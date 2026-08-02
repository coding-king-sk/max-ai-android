package com.max.ai.data.repository

import com.max.ai.data.local.dao.MemoryDao
import com.max.ai.data.local.entity.MemoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryRepository @Inject constructor(private val dao: MemoryDao) {

    fun getAllMemories(): Flow<List<MemoryEntity>> = dao.all()

    suspend fun remember(key: String, value: String) {
        val existing = dao.byKey(key)
        if (existing != null) {
            dao.insert(existing.copy(value = value, ua = System.currentTimeMillis()))
        } else {
            dao.insert(MemoryEntity(key = key, value = value))
        }
    }

    suspend fun recall(query: String): List<MemoryEntity> = dao.search(query)

    suspend fun forget(key: String) {
        dao.del(key)
    }

    suspend fun clearAll() {
        dao.clear()
    }
}
