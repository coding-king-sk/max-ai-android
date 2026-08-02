package com.max.ai.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.max.ai.data.local.entity.CommandLogEntity
import com.max.ai.data.local.entity.MemoryEntity
import com.max.ai.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {
    @Query("SELECT * FROM memories ORDER BY updated_at DESC")
    fun all(): Flow<List<MemoryEntity>>

    @Query("SELECT * FROM memories WHERE `key` = :k LIMIT 1")
    suspend fun byKey(k: String): MemoryEntity?

    @Query("SELECT * FROM memories WHERE `key` LIKE '%' || :q || '%' OR value LIKE '%' || :q || '%'")
    suspend fun search(q: String): List<MemoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(e: MemoryEntity)

    @Query("DELETE FROM memories WHERE `key` = :k")
    suspend fun deleteKey(k: String)

    @Query("DELETE FROM memories")
    suspend fun deleteAll()
}

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes ORDER BY created_at DESC")
    fun all(): Flow<List<NoteEntity>>

    @Insert
    suspend fun insert(n: NoteEntity): Long

    @Delete
    suspend fun delete(n: NoteEntity)
}

@Dao
interface CommandLogDao {
    @Query("SELECT * FROM command_logs ORDER BY timestamp DESC LIMIT :l")
    fun recent(l: Int = 50): Flow<List<CommandLogEntity>>

    @Insert
    suspend fun insert(log: CommandLogEntity)

    @Query("DELETE FROM command_logs")
    suspend fun clear()
}
