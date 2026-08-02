package com.max.ai.core.voice

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.max.ai.core.network.ApiClient
import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import java.util.Locale
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext

class WakeWordEngine {
    private val _listening = MutableStateFlow(false)
    val listening: StateFlow<Boolean> = _listening
    private var cb: (() -> Unit)? = null
    fun startListening(onDetect: () -> Unit, onError: (String) -> Unit) { cb = onDetect; _listening.value = true }
    fun stop() { _listening.value = false; cb = null }
    companion object { const val WAKE = "Hey Max" }
}

class GeminiLiveClient @Inject constructor(private val api: ApiClient) {
    private val _active = MutableStateFlow(false)
    val active: StateFlow<Boolean> = _active
    suspend fun connect(key: String) { _active.value = true }
    suspend fun sendText(text: String) = api.sendToGemini(text)
    fun disconnect() { _active.value = false }
}

class SttEngine {
    private val _listening = MutableStateFlow(false)
    val listening: StateFlow<Boolean> = _listening
    private var cb: ((String) -> Unit)? = null
    fun startListening(onResult: (String) -> Unit, onError: () -> Unit) { cb = onResult; _listening.value = true }
    fun stop() { _listening.value = false }
}

class TtsEngine @Inject constructor(@ApplicationContext private val ctx: Context) {
    private var tts: TextToSpeech? = null
    private val _speaking = MutableStateFlow(false)
    val speaking: StateFlow<Boolean> = _speaking
    fun init(onReady: () -> Unit = {}) {
        tts = TextToSpeech(ctx) {
            if (it == TextToSpeech.SUCCESS) { tts?.language = Locale("hi", "IN"); onReady() }
        }
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(id: String) { _speaking.value = true }
            override fun onDone(id: String) { _speaking.value = false }
            @Deprecated("Deprecated") override fun onError(id: String) { _speaking.value = false }
        })
    }
    fun speak(text: String, lang: String = "hi") {
        tts?.language = if (lang == "hi") Locale("hi", "IN") else Locale.US
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "max_${System.currentTimeMillis()}")
    }
    fun stop() { tts?.stop() }
    fun shutdown() { tts?.shutdown(); tts = null }
}
