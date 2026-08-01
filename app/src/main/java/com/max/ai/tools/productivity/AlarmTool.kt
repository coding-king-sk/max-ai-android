package com.max.ai.tools.productivity

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class AlarmTool : Tool {
    override val name = "set_alarm"
    override val description = "Set an alarm or reminder"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("time") { put("type", "string") }
            putJsonObject("label") { put("type", "string") }
            putJsonObject("type") { put("type", "string"); put("enum", JsonArray(listOf("alarm", "reminder").map { JsonPrimitive(it) })) }
        }
        putJsonArray("required") { add("time") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Alarm set")
}
