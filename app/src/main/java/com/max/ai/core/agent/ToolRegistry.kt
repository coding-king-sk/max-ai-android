package com.max.ai.core.agent

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

@Singleton
class ToolRegistry @Inject constructor() {

    private val tools = mutableMapOf<String, Tool>()

    fun register(tool: Tool) {
        tools[tool.name] = tool
    }

    fun registerAll(vararg toolList: Tool) {
        toolList.forEach { register(it) }
    }

    fun get(name: String): Tool? = tools[name]

    fun getAll(): List<Tool> = tools.values.toList()

    suspend fun execute(toolCall: ToolCall): ToolResult {
        val tool = tools[toolCall.name]
            ?: return ToolResult(false, "Tool not found: ${toolCall.name}")
        return try {
            tool.execute(toolCall.arguments)
        } catch (e: Exception) {
            ToolResult(false, "Tool error: ${e.message}")
        }
    }

    fun buildToolSchemas(): JsonObject = buildJsonObject {
        put("type", "function")
        tools.forEach { (_, tool) ->
            put(tool.name, buildJsonObject {
                put("name", tool.name)
                put("description", tool.description)
                put("parameters", tool.parameters)
            })
        }
    }

    fun getToolDescriptions(): String = tools.values.joinToString("\n\n") { tool ->
        buildString {
            append("## ${tool.name}\n")
            append("${tool.description}\n")
            append("Parameters: ${tool.parameters}")
        }
    }

    val toolCount: Int get() = tools.size
}
