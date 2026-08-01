package com.max.ai.tools.communication

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class CallTool : Tool {
    override val name = "make_call"
    override val description = "Make a phone call to a contact"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("contactName") { put("type", "string"); put("description", "Contact to call") }
        }
        putJsonArray("required") { add("contactName") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        val contact = args["contactName"] ?: return ToolResult(false, "Contact name required")
        return ToolResult(true, "Calling $contact", mapOf("contact" to contact))
    }
}
