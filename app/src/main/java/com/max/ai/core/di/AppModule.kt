package com.max.ai.core.di

import com.max.ai.data.local.db.AppDatabase
import com.max.ai.data.local.datastore.UserPreferences
import com.max.ai.data.repository.MemoryRepository
import com.max.ai.data.repository.NotesRepository
import com.max.ai.core.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideAppDatabase(app: android.app.Application): AppDatabase = AppDatabase.getInstance(app)

    @Provides @Singleton
    fun provideUserPreferences(app: android.app.Application): UserPreferences = UserPreferences(app)

    @Provides @Singleton
    fun provideMemoryRepository(db: AppDatabase): MemoryRepository = MemoryRepository(db.memoryDao())

    @Provides @Singleton
    fun provideNotesRepository(db: AppDatabase): NotesRepository = NotesRepository(db.notesDao())

    @Provides @Singleton
    fun provideApiClient(): ApiClient = ApiClient()

    @Provides @Singleton
    fun provideWakeWordEngine(): com.max.ai.core.wakeword.WakeWordEngine = com.max.ai.core.wakeword.WakeWordEngine()

    @Provides @Singleton
    fun provideGeminiLiveClient(api: ApiClient): com.max.ai.core.voice.GeminiLiveClient = com.max.ai.core.voice.GeminiLiveClient(api)

    @Provides @Singleton
    fun provideSttEngine(): com.max.ai.core.voice.SttEngine = com.max.ai.core.voice.SttEngine()

    @Provides @Singleton
    fun provideTtsEngine(): com.max.ai.core.voice.TtsEngine = com.max.ai.core.voice.TtsEngine()
}
