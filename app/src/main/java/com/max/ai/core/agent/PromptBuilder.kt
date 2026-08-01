package com.max.ai.core.agent

import com.max.ai.data.repository.MemoryRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromptBuilder @Inject constructor(private val repo: MemoryRepository) {
    fun buildSystemPrompt(registry: ToolRegistry): String = buildString {
        append("You are Max AI, a Hinglish-first voice assistant for Android.\n\n")
        append("## Rules\n- User speaks Hinglish. Understand both.\n")
        append("- Respond in SAME style as user. Keep responses CONCISE.\n")
        append("- Use tools for real actions. Confirm destructive ones.\n\n")
        val memories = runBlocking { repo.getAllMemories().firstOrNull() ?: emptyList() }
        if (memories.isNotEmpty()) {
            append("## Remembered\n")
            memories.forEach { append("- ${it.key}: ${it.value}\n") }
            append("\n")
        }
        append("## Tools\n${registry.getToolDescriptions()}")
    }
}
