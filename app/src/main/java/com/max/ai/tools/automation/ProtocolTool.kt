package com.max.ai.tools.automation

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class ProtocolTool : Tool {
    override val name = "execute_protocol"
    override val description = "Execute a saved workflow protocol"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("protocolName") { put("type", "string") }
        }
        putJsonArray("required") { add("protocolName") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Protocol executed")
}
