package com.max.ai.tools.device

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class BiometricTool : Tool {
    override val name = "biometric_lock"
    override val description = "Enable or check biometric lock"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("action") { put("type", "string"); put("enum", JsonArray(listOf("enable", "disable", "check").map { JsonPrimitive(it) })) }
        }
        putJsonArray("required") { add("action") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Biometric ${args["action"]} done")
}

class ImageGenTool : Tool {
    override val name = "generate_image"
    override val description = "Generate an image using AI"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("prompt") { put("type", "string") }
        }
        putJsonArray("required") { add("prompt") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Image generated")
}

class SelfDestructTool : Tool {
    override val name = "self_destruct"
    override val description = "Clear all local data (memories, notes, logs)"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("confirm") { put("type", "string"); put("description", "Type DELETE to confirm") }
        }
        putJsonArray("required") { add("confirm") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        if (args["confirm"]?.uppercase() != "DELETE") return ToolResult(false, "Confirmation required")
        return ToolResult(true, "All data cleared")
    }
}

class WidgetForgeTool : Tool {
    override val name = "forge_widget"
    override val description = "Create a floating widget"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("type") { put("type", "string"); put("enum", JsonArray(listOf("timer", "clock", "stock", "weather").map { JsonPrimitive(it) })) }
            putJsonObject("params") { put("type", "string") }
        }
        putJsonArray("required") { add("type") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Widget forged")
}
