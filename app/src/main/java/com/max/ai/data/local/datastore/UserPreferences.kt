package com.max.ai.data.local.datastore
import android.content.Context; import androidx.datastore.core.DataStore; import androidx.datastore.preferences.core.*; import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow; import kotlinx.coroutines.flow.first; import kotlinx.coroutines.flow.map
private val Context.ds: DataStore<Preferences> by preferencesDataStore(name = "max_settings")
class UserPreferences(private val c: Context) {
    companion object { val K = stringPreferencesKey("gemini_key") }
    val key: Flow<String> = c.ds.data.map { it[K] ?: "" }
    suspend fun set(k: String) { c.ds.edit { it[K] = k } }
    suspend fun get(): String = c.ds.data.first()[K] ?: ""
}