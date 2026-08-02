package com.max.ai.core.agent

import com.max.ai.data.repository.MemoryRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromptBuilder @Inject constructor(private val repo: MemoryRepository) {
    fun build(registry: ToolRegistry): String = buildString {
        append("You are Max AI, a Hinglish-first voice assistant for Android.\n\n")
        append("Rules: Speak Hinglish. Use tools for actions. Confirm destructive ones.\n\n")
        val mems = runBlocking { repo.getAllMemories().firstOrNull() ?: emptyList() }
        if (mems.isNotEmpty()) { append("## Memory\n"); mems.forEach { append("- ${it.key}: ${it.value}\n") }; append("\n") }
        append("## Tools\n${registry.getDescriptions()}")
    }
}
