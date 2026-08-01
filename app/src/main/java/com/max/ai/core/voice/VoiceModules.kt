package com.max.ai.core.voice

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.max.ai.core.network.ApiClient
import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

class WakeWordEngine {
    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening
    private var callback: (() -> Unit)? = null
    fun startListening(onDetect: () -> Unit, onError: (String) -> Unit) { callback = onDetect; _isListening.value = true }
    fun simulateWakeWord() { callback?.invoke() }
    fun stop() { _isListening.value = false; callback = null }
    companion object { const val WAKE_WORD = "Hey Max" }
}

class GeminiLiveClient @Inject constructor(private val api: ApiClient) {
    private val _isActive = MutableStateFlow(false)
    val isActive = _isActive
    suspend fun connect(key: String) { _isActive.value = true }
    suspend fun sendText(text: String) = api.sendToGemini(text)
    fun disconnect() { _isActive.value = false }
}

class SttEngine {
    private val _isListening = MutableStateFlow(false)
    val isListening = _isListening
    private var cb: ((String) -> Unit)? = null
    fun startListening(onResult: (String) -> Unit, onError: () -> Unit) { cb = onResult; _isListening.value = true }
    fun simulateResult(text: String) { _isListening.value = false; cb?.invoke(text) }
    fun stop() { _isListening.value = false }
}

class TtsEngine @Inject constructor(@ApplicationContext private val ctx: Context) {
    private var tts: TextToSpeech? = null
    private val _isSpeaking = MutableStateFlow(false)
    val isSpeaking = _isSpeaking
    fun init(onReady: () -> Unit) {
        tts = TextToSpeech(ctx) {
            if (it == TextToSpeech.SUCCESS) { tts?.language = Locale("hi", "IN"); onReady() }
        }
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(id: String) { _isSpeaking.value = true }
            override fun onDone(id: String) { _isSpeaking.value = false }
            @Deprecated("Deprecated") override fun onError(id: String) { _isSpeaking.value = false }
        })
    }
    fun speak(text: String, lang: String = "hi") {
        tts?.language = if (lang == "hi") Locale("hi", "IN") else Locale.US
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "max_${System.currentTimeMillis()}")
    }
    fun stop() { tts?.stop() }
    fun shutdown() { tts?.shutdown(); tts = null }
}
