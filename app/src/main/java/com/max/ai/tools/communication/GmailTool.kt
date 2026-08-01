package com.max.ai.tools.communication

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*
import javax.inject.Inject

class GmailTool @Inject constructor() : Tool {
    override val name = "gmail"
    override val description = "Read, draft, or send Gmail emails"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("action") { put("type", "string"); put("enum", JsonArray(listOf(JsonPrimitive("read"), JsonPrimitive("draft"), JsonPrimitive("send")))) }
            putJsonObject("to") { put("type", "string") }
            putJsonObject("subject") { put("type", "string") }
            putJsonObject("body") { put("type", "string") }
            putJsonObject("filter") { put("type", "string") }
        }
        putJsonArray("required") { add("action") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        return ToolResult(true, "Gmail action: ${args["action"]} done")
    }
}
