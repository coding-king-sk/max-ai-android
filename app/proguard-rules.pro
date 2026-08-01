# Gemini Live API
-keep class com.max.ai.core.voice.GeminiLiveClient { *; }
-keep class com.max.ai.core.agent.** { *; }

# Porcupine
-keep class ai.picovoice.porcupine.** { *; }

# Ktor
-keep class io.ktor.** { *; }
-keep class kotlinx.serialization.** { *; }

# Room
-keep class com.max.ai.data.local.entity.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
