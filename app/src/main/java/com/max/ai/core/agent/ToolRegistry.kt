package com.max.ai.core.agent

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Singleton
class ToolRegistry @Inject constructor() {
    private val tools = mutableMapOf<String, Tool>()
    fun register(tool: Tool) { tools[tool.name] = tool }
    fun registerAll(vararg toolList: Tool) { toolList.forEach { register(it) } }
    fun get(name: String): Tool? = tools[name]
    fun getAll(): List<Tool> = tools.values.toList()
    suspend fun execute(call: ToolCall): ToolResult = tools[call.name]?.execute(call.arguments) ?: ToolResult(false, "Tool not found: ${call.name}")
    fun getDescriptions(): String = tools.values.joinToString("\n") { "## ${it.name}\n${it.description}" }
}
