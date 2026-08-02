package com.max.ai.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.max.ai.MaxApplication
import com.max.ai.R
import com.max.ai.core.agent.AgentCore
import com.max.ai.core.voice.SttEngine
import com.max.ai.core.voice.WakeWordEngine
import com.max.ai.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MaxForegroundService : Service() {

    @Inject lateinit var wakeWord: WakeWordEngine
    @Inject lateinit var stt: SttEngine
    @Inject lateinit var agent: AgentCore

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var listening = false

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startAsForeground()
        startWakeWord()
    }

    private fun startAsForeground() {
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = NotificationCompat.Builder(this, MaxApplication.CHANNEL)
            .setContentTitle(getString(R.string.foreground_notification_title))
            .setContentText(getString(R.string.foreground_notification_text))
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun startWakeWord() {
        wakeWord.startListening(
            onDetect = { startVoice() },
            onError = { message -> android.util.Log.e(TAG, message) }
        )
    }

    private fun startVoice() {
        if (listening) return
        listening = true
        stt.startListening(
            onResult = { text ->
                listening = false
                processCommand(text)
            },
            onError = { listening = false }
        )
    }

    private fun processCommand(text: String) {
        scope.launch {
            try {
                val response = agent.process(text)
                if (response.toolCalls.isNotEmpty()) {
                    agent.executeCalls(response.toolCalls)
                }
            } catch (e: Exception) {
                android.util.Log.e(TAG, e.message ?: "Unknown error")
            }
        }
    }

    override fun onDestroy() {
        wakeWord.stop()
        stt.stop()
        scope.cancel()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "MaxForegroundService"
        private const val NOTIFICATION_ID = 1
    }
}
