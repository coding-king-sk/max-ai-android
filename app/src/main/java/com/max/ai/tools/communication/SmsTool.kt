package com.max.ai.tools.communication

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class SmsTool : Tool {
    override val name = "send_sms"
    override val description = "Send an SMS message"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("contactName") { put("type", "string") }
            putJsonObject("message") { put("type", "string") }
        }
        putJsonArray("required") { add("contactName"); add("message") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        return ToolResult(true, "SMS sent")
    }
}
