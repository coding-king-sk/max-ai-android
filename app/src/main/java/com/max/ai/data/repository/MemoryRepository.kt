package com.max.ai.data.repository

import com.max.ai.data.local.dao.MemoryDao
import com.max.ai.data.local.entity.MemoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryRepository @Inject constructor(private val dao: MemoryDao) {
    fun getAllMemories(): Flow<List<MemoryEntity>> = dao.getAll()
    suspend fun remember(key: String, value: String) {
        val existing = dao.getByKey(key)
        dao.insert(if (existing != null) existing.copy(value = value, updatedAt = System.currentTimeMillis()) else MemoryEntity(key = key, value = value))
    }
    suspend fun recall(query: String): List<MemoryEntity> = dao.search(query)
    suspend fun forget(key: String) { dao.deleteByKey(key) }
    suspend fun clearAll() { dao.deleteAll() }
}
