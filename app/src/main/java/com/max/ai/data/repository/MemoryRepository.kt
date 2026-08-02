package com.max.ai.data.repository
import com.max.ai.data.local.dao.MemoryDao; import com.max.ai.data.local.entity.MemoryEntity; import kotlinx.coroutines.flow.Flow
import javax.inject.Inject; import javax.inject.Singleton
@Singleton class MemoryRepository @Inject constructor(private val d: MemoryDao) {
    fun all(): Flow<List<MemoryEntity>> = d.all()
    suspend fun remember(key: String, value: String) { val e = d.byKey(key); d.insert(if (e != null) e.copy(value = value, updatedAt = System.currentTimeMillis()) else MemoryEntity(key = key, value = value)) }
    suspend fun recall(q: String): List<MemoryEntity> = d.search(q)
    suspend fun forget(k: String) { d.deleteKey(k) }
}