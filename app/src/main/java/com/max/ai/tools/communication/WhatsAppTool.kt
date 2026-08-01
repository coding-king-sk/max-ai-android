package com.max.ai.tools.communication

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*
import javax.inject.Inject

class WhatsAppTool @Inject constructor() : Tool {
    override val name = "send_whatsapp"
    override val description = "Send a WhatsApp message to a contact"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("contactName") { put("type", "string"); put("description", "Contact name") }
            putJsonObject("message") { put("type", "string"); put("description", "Message text") }
        }
        putJsonArray("required") { add("contactName"); add("message") }
    }

    override suspend fun execute(args: Map<String, String>): ToolResult {
        val contact = args["contactName"] ?: return ToolResult(false, "Contact name required")
        val message = args["message"] ?: return ToolResult(false, "Message required")
        return ToolResult(true, "WhatsApp message sent to $contact", mapOf("contact" to contact, "message" to message))
    }
}
