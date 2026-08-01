package com.max.ai.tools.memory

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import com.max.ai.data.repository.MemoryRepository
import kotlinx.serialization.json.*
import javax.inject.Inject

class RememberTool @Inject constructor(private val repo: MemoryRepository) : Tool {
    override val name = "remember"
    override val description = "Save something to Max's memory"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("key") { put("type", "string") }
            putJsonObject("value") { put("type", "string") }
        }
        putJsonArray("required") { add("key"); add("value") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        repo.remember(args["key"]!!, args["value"]!!)
        return ToolResult(true, "Remembered: ${args["key"]}")
    }
}

class RecallTool @Inject constructor(private val repo: MemoryRepository) : Tool {
    override val name = "recall"
    override val description = "Recall something from Max's memory"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("query") { put("type", "string") }
        }
        putJsonArray("required") { add("query") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        val results = repo.recall(args["query"]!!)
        return ToolResult(true, if (results.isEmpty()) "Nothing found" else results.joinToString("\n") { "${it.key}: ${it.value}" })
    }
}
