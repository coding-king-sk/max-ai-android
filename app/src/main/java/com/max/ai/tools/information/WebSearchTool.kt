package com.max.ai.tools.information
import com.max.ai.core.agent.*; import kotlinx.serialization.json.*; import javax.inject.Inject
class WebSearchTool @Inject constructor() : Tool { override val name = "web_search"; override val description = "Search the web"; override val parameters = buildJsonObject { put("type", "object"); putJsonObject("properties") { putJsonObject("query") { put("type", "string") } }; putJsonArray("required") { add("query") } }; override suspend fun execute(args: Map<String, String>) = ToolResult(true, "Search results for: ${args["query"]}") }
