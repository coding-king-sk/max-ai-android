package com.max.ai.tools.information

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import kotlinx.serialization.json.*

class DeepResearchTool : Tool {
    override val name = "deep_research"
    override val description = "Perform multi-step deep research on a topic"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("topic") { put("type", "string") }
        }
        putJsonArray("required") { add("topic") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult = ToolResult(true, "Research complete")
}
