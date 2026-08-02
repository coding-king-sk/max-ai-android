package com.max.ai.core.agent

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AgentCore @Inject constructor(
    private val registry: ToolRegistry,
    private val prompt: PromptBuilder
) {
    suspend fun process(userInput: String, history: List<String> = emptyList()): AgentResponse {
        val ctx = buildString {
            appendLine(prompt.build(registry))
            history.forEach { appendLine(it) }
            appendLine("User: $userInput")
        }
        return AgentResponse(text = "Processing: $userInput")
    }
    suspend fun executeCalls(calls: List<ToolCall>): List<ToolResult> = calls.map { registry.execute(it) }
}
