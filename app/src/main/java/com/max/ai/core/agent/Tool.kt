package com.max.ai.core.agent

import kotlinx.serialization.json.JsonObject

interface Tool {
    val name: String
    val description: String
    val parameters: JsonObject

    suspend fun execute(args: Map<String, String>): ToolResult
}

data class ToolResult(
    val success: Boolean,
    val message: String,
    val data: Map<String, String> = emptyMap()
)

data class ToolCall(
    val id: String,
    val name: String,
    val arguments: Map<String, String>
)
