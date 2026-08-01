package com.max.ai.tools.information

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class LocationTool : Tool {
    override val name = "location"
    override val description = "Get current location, show map, or get directions"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("action") { put("type", "string"); put("enum", JsonArray(listOf("current", "map", "directions").map { JsonPrimitive(it) })) }
            putJsonObject("from") { put("type", "string") }
            putJsonObject("to") { put("type", "string") }
        }
        putJsonArray("required") { add("action") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Location action done")
}
