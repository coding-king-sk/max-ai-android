package com.max.ai.core.agent

import kotlinx.serialization.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Serializable
data class AgentResponse(
    val text: String,
    val toolCalls: List<ToolCall> = emptyList(),
    val isError: Boolean = false
)

@Singleton
class AgentCore @Inject constructor(
    private val toolRegistry: ToolRegistry,
    private val promptBuilder: PromptBuilder
) {
    suspend fun process(
        userInput: String,
        conversationHistory: List<String> = emptyList()
    ): AgentResponse {
        val systemPrompt = promptBuilder.buildSystemPrompt(toolRegistry)

        val context = buildString {
            appendLine(systemPrompt)
            conversationHistory.forEach { appendLine(it) }
            appendLine("User: $userInput")
        }

        return AgentResponse(text = "Processing: $userInput")
    }

    suspend fun executeToolCalls(calls: List<ToolCall>): List<ToolResult> {
        return calls.map { toolRegistry.execute(it) }
    }
}
