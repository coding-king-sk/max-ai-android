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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

@Module @InstallIn(SingletonComponent::class)
object AppModule {
    @Provides @Singleton
    fun provideDb(a: android.app.Application): AppDatabase = AppDatabase.getInstance(a)
    @Provides @Singleton
    fun providePrefs(a: android.app.Application): UserPreferences = UserPreferences(a)
    @Provides @Singleton
    fun provideMemRepo(db: AppDatabase): MemoryRepository = MemoryRepository(db.memoryDao())
    @Provides @Singleton
    fun provideNotesRepo(db: AppDatabase): NotesRepository = NotesRepository(db.notesDao())
    @Provides @Singleton
    fun provideApi(): ApiClient = ApiClient()
    @Provides @Singleton
    fun provideWakeWord(): WakeWordEngine = WakeWordEngine()
    @Provides @Singleton
    fun provideGemini(api: ApiClient): GeminiLiveClient = GeminiLiveClient(api)
    @Provides @Singleton
    fun provideStt(): SttEngine = SttEngine()
    @Provides @Singleton
    fun provideTts(@ApplicationContext ctx: Context): TtsEngine = TtsEngine(ctx)
}
