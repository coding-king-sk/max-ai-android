package com.max.ai.services

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.max.ai.MaxApplication
import com.max.ai.R
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MaxForegroundService : Service() {
    @Inject lateinit var wakeWord: com.max.ai.core.voice.WakeWordEngine
    @Inject lateinit var stt: com.max.ai.core.voice.SttEngine
    @Inject lateinit var agent: com.max.ai.core.agent.AgentCore
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var listening = false
    override fun onBind(i: Intent?) = null
    override fun onCreate() { super.onCreate(); startForeground(); startWakeWord() }
    private fun startForeground() {
        val pi = PendingIntent.getActivity(this, 0, Intent(this, com.max.ai.ui.MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val n = NotificationCompat.Builder(this, MaxApplication.CHANNEL_SERVICE).setContentTitle(getString(R.string.foreground_notification_title)).setContentText(getString(R.string.foreground_notification_text)).setSmallIcon(android.R.drawable.ic_btn_speak_now).setContentIntent(pi).setOngoing(true).setPriority(NotificationCompat.PRIORITY_LOW).build()
        startForeground(1, n)
    }
    private fun startWakeWord() { wakeWord.startListening(onDetect = { startVoice() }, onError = {}) }
    private fun startVoice() { if (listening) return; listening = true; stt.startListening(onResult = { text -> listening = false; processCommand(text) }, onError = { listening = false }) }
    private fun processCommand(text: String) { scope.launch { try { val r = agent.process(text); if (r.toolCalls.isNotEmpty()) agent.executeToolCalls(r.toolCalls) } catch (e: Exception) { android.util.Log.e("MaxFG", e.message.toString()) } } }
    override fun onDestroy() { wakeWord.stop(); stt.stop(); super.onDestroy() }
}
