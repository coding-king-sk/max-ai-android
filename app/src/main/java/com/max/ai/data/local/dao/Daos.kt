package com.max.ai.data.local.dao

import androidx.room.*
import com.max.ai.data.local.entity.MemoryEntity
import com.max.ai.data.local.entity.NoteEntity
import com.max.ai.data.local.entity.CommandLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoryDao {
    @Query("SELECT * FROM memories ORDER BY updated_at DESC")
    fun getAll(): Flow<List<MemoryEntity>>
    @Query("SELECT * FROM memories WHERE `key` = :key LIMIT 1")
    suspend fun getByKey(key: String): MemoryEntity?
    @Query("SELECT * FROM memories WHERE `key` LIKE '%' || :q || '%' OR value LIKE '%' || :q || '%'")
    suspend fun search(q: String): List<MemoryEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(e: MemoryEntity)
    @Query("DELETE FROM memories WHERE `key` = :key")
    suspend fun deleteByKey(key: String)
    @Query("DELETE FROM memories")
    suspend fun deleteAll()
}

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes ORDER BY created_at DESC")
    fun getAll(): Flow<List<NoteEntity>>
    @Insert suspend fun insert(n: NoteEntity): Long
    @Update suspend fun update(n: NoteEntity)
    @Delete suspend fun delete(n: NoteEntity)
}

@Dao
interface CommandLogDao {
    @Query("SELECT * FROM command_logs ORDER BY timestamp DESC LIMIT :limit")
    fun getRecent(limit: Int = 50): Flow<List<CommandLogEntity>>
    @Insert suspend fun insert(log: CommandLogEntity)
    @Query("DELETE FROM command_logs") suspend fun clear()
}
