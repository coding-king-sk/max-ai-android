package com.max.ai.tools.productivity

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class CalendarTool : Tool {
    override val name = "calendar"
    override val description = "Query or create calendar events"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("action") { put("type", "string"); put("enum", JsonArray(listOf("query", "create").map { JsonPrimitive(it) })) }
            putJsonObject("title") { put("type", "string") }
            putJsonObject("date") { put("type", "string") }
        }
        putJsonArray("required") { add("action") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Calendar action done")
}
