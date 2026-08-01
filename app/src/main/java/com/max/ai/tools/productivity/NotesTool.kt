package com.max.ai.tools.productivity

import com.max.ai.core.agent.Tool
import com.max.ai.core.agent.ToolResult
import com.max.ai.data.repository.NotesRepository
import kotlinx.serialization.json.*
import javax.inject.Inject

class NotesTool @Inject constructor(private val repo: NotesRepository) : Tool {
    override val name = "save_note"
    override val description = "Save a note to local storage"
    override val parameters = buildJsonObject {
        put("type", "object")
        putJsonObject("properties") {
            putJsonObject("title") { put("type", "string") }
            putJsonObject("content") { put("type", "string") }
        }
        putJsonArray("required") { add("title"); add("content") }
    }
    override suspend fun execute(args: Map<String, String>): ToolResult {
        val id = repo.save(args["title"]!!, args["content"]!!)
        return ToolResult(true, "Note saved", mapOf("id" to id.toString()))
    }
}
