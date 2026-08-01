package com.max.ai.tools.system

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class FlashlightTool : Tool {
    override val name = "flashlight"
    override val description = "Toggle phone flashlight on/off"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("action") { put("type", "string"); put("enum", JsonArray(listOf(JsonPrimitive("on"), JsonPrimitive("off")))) }
        }
        putJsonArray("required") { add("action") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        val action = args["action"] ?: return ToolResult(false, "Action required")
        return ToolResult(true, "Flashlight turned $action")
    }
}
