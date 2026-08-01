package com.max.ai.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "max_settings")

class UserPreferences(private val context: Context) {
    companion object {
        val GEMINI_KEY = stringPreferencesKey("gemini_api_key")
        val WAKE_WORD = booleanPreferencesKey("wake_word_enabled")
        val OVERLAY = booleanPreferencesKey("overlay_enabled")
        val LANG = stringPreferencesKey("language_pref")
    }

    val geminiKey: Flow<String> = context.dataStore.data.map { it[GEMINI_KEY] ?: "" }
    val wakeWordEnabled: Flow<Boolean> = context.dataStore.data.map { it[WAKE_WORD] ?: true }

    suspend fun setGeminiKey(key: String) { context.dataStore.edit { it[GEMINI_KEY] = key } }
    suspend fun setWakeWordEnabled(on: Boolean) { context.dataStore.edit { it[WAKE_WORD] = on } }
    suspend fun getGeminiKey(): String = context.dataStore.data.first()[GEMINI_KEY] ?: ""
}
