package com.max.ai.core.agent

import com.max.ai.data.repository.MemoryRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromptBuilder @Inject constructor(
    private val memoryRepository: MemoryRepository
) {
    private val json = Json { prettyPrint = true }

    fun buildSystemPrompt(toolRegistry: ToolRegistry): String = buildString {
        appendLine("You are Max AI, a Hinglish-first voice assistant for Android.")
        appendLine()
        appendLine("## Rules")
        appendLine("- User speaks Hinglish (Hindi-English mix). Understand both.")
        appendLine("- Respond in the SAME style: Hinglish for Hinglish, English for English.")
        appendLine("- Use tools to execute real actions on the phone.")
        appendLine("- Keep responses CONCISE (2-3 sentences max unless asked).")
        appendLine("- Confirm before calls, messages, and destructive actions.")
        appendLine()

        val memories = memoryRepository.getAllMemories()
        if (memories.isNotEmpty()) {
            appendLine("## Remembered Facts")
            memories.forEach { appendLine("- ${it.key}: ${it.value}") }
            appendLine()
        }

        appendLine("## Available Tools")
        appendLine(toolRegistry.getToolDescriptions())
    }

    fun buildFunctionSchemas(toolRegistry: ToolRegistry): List<JsonObject> {
        return toolRegistry.getAll().map { tool -> tool.parameters }
    }
}
