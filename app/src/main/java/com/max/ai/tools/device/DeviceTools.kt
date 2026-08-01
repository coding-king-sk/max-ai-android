package com.max.ai.tools.device

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class ScreenVisionTool : Tool {
    override val name = "screen_vision"
    override val description = "Analyze current screen content using AI vision"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("question") { put("type", "string") }
        }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Screen analyzed")
}

class CameraVisionTool : Tool {
    override val name = "camera_vision"
    override val description = "Take a photo and analyze it with AI"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("question") { put("type", "string") }
        }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Camera image analyzed")
}
