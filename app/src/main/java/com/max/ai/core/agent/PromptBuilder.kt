package com.max.ai.core.agent

import com.max.ai.data.repository.MemoryRepository
import com.max.ai.data.local.entity.MemoryEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PromptBuilder @Inject constructor(private val repo: MemoryRepository) {

    fun build(registry: ToolRegistry): String {
        val sb = StringBuilder()
        sb.append("You are Max AI, a Hinglish-first voice assistant for Android.\n\n")
        sb.append("Rules:\n")
        sb.append("- User speaks Hinglish. Understand Hindi and English.\n")
        sb.append("- Respond in the same style as the user.\n")
        sb.append("- Use tools for real actions. Confirm destructive ones.\n")
        sb.append("- Keep responses concise.\n\n")

        val memories: List<MemoryEntity> = runBlocking {
            repo.getAllMemories().firstOrNull() ?: emptyList()
        }

        if (memories.isNotEmpty()) {
            sb.append("## Memory\n")
            for (memory in memories) {
                sb.append("- ")
                sb.append(memory.key)
                sb.append(": ")
                sb.append(memory.value)
                sb.append("\n")
            }
            sb.append("\n")
        }

        sb.append("## Tools\n")
        sb.append(registry.getDescriptions())
        return sb.toString()
    }
}
