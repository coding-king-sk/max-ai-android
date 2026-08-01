package com.max.ai.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.speech.RecognizerIntent
import androidx.core.app.NotificationCompat
import com.max.ai.MaxApplication
import com.max.ai.R
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaxForegroundService : Service() {

    @Inject lateinit var wakeWordEngine: com.max.ai.core.wakeword.WakeWordEngine
    @Inject lateinit var sttEngine: com.max.ai.core.voice.SttEngine
    @Inject lateinit var agentCore: com.max.ai.core.agent.AgentCore
    @Inject lateinit var toolRegistry: com.max.ai.core.agent.ToolRegistry

    private var isListening = false

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground()
        startWakeWordDetection()
    }

    private fun startForeground() {
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, com.max.ai.ui.MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(this, MaxApplication.CHANNEL_SERVICE)
            .setContentTitle(getString(R.string.foreground_notification_title))
            .setContentText(getString(R.string.foreground_notification_text))
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        startForeground(1, notification)
    }

    private fun startWakeWordDetection() {
        wakeWordEngine.startListening(
            onWakeWordDetected = {
                startVoiceRecognition()
            },
            onError = { error ->
                android.util.Log.e(TAG, "Wake word error: $error")
            }
        )
    }

    private fun startVoiceRecognition() {
        if (isListening) return
        isListening = true

        sttEngine.startListening(
            onResult = { text ->
                isListening = false
                processCommand(text)
            },
            onError = {
                isListening = false
            }
        )
    }

    private fun processCommand(text: String) {
        kotlinx.coroutines.MainScope().launch {
            try {
                val response = agentCore.process(text)
                if (response.toolCalls.isNotEmpty()) {
                    agentCore.executeToolCalls(response.toolCalls)
                }
            } catch (e: Exception) {
                android.util.Log.e(TAG, "Command error: ${e.message}")
            }
        }
    }

    override fun onDestroy() {
        wakeWordEngine.stop()
        sttEngine.stop()
        super.onDestroy()
    }

    companion object {
        private const val TAG = "MaxForegroundService"
    }
}

private fun kotlinx.coroutines.CoroutineScope.launch(action: suspend () -> Unit) {
    kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
        action()
    }
}

private fun kotlinx.coroutines.MainScope(): kotlinx.coroutines.CoroutineScope {
    return kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Main + kotlinx.coroutines.SupervisorJob())
}
