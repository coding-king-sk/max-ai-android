package com.max.ai.core.di

import com.max.ai.data.local.db.AppDatabase
import com.max.ai.data.local.datastore.UserPreferences
import com.max.ai.data.repository.MemoryRepository
import com.max.ai.data.repository.NotesRepository
import com.max.ai.core.network.ApiClient
import com.max.ai.core.voice.WakeWordEngine
import com.max.ai.core.voice.GeminiLiveClient
import com.max.ai.core.voice.SttEngine
import com.max.ai.core.voice.TtsEngine
import dagger.Module; dagger.Provides
import dagger.hilt.InstallIn; dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

@Module @InstallIn(SingletonComponent::class) object AppModule {
    @Provides @Singleton fun db(a: android.app.Application) = AppDatabase.getInstance(a)
    @Provides @Singleton fun prefs(a: android.app.Application) = UserPreferences(a)
    @Provides @Singleton fun memRepo(db: AppDatabase) = MemoryRepository(db.memoryDao())
    @Provides @Singleton fun notesRepo(db: AppDatabase) = NotesRepository(db.notesDao())
    @Provides @Singleton fun api() = ApiClient()
    @Provides @Singleton fun wakeWord() = WakeWordEngine()
    @Provides @Singleton fun gemini(a: ApiClient) = GeminiLiveClient(a)
    @Provides @Singleton fun stt() = SttEngine()
    @Provides @Singleton fun tts(@ApplicationContext c: Context) = TtsEngine(c)
}
