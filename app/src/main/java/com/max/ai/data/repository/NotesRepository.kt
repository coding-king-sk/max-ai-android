package com.max.ai.data.repository

import com.max.ai.data.local.dao.NotesDao
import com.max.ai.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepository @Inject constructor(private val dao: NotesDao) {

    fun getAllNotes(): Flow<List<NoteEntity>> = dao.all()

    suspend fun save(title: String, content: String): Long =
        dao.insert(NoteEntity(title = title, content = content))

    suspend fun delete(note: NoteEntity) {
        dao.delete(note)
    }
}
