package com.max.ai.data.repository
import com.max.ai.data.local.dao.NotesDao; import com.max.ai.data.local.entity.NoteEntity; import kotlinx.coroutines.flow.Flow
import javax.inject.Inject; import javax.inject.Singleton
@Singleton class NotesRepository @Inject constructor(private val d: NotesDao) {
    fun all(): Flow<List<NoteEntity>> = d.all()
    suspend fun save(t: String, c: String): Long = d.insert(NoteEntity(title = t, content = c))
    suspend fun delete(n: NoteEntity) { d.delete(n) }
}