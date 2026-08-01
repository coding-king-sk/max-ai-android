package com.max.ai.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import com.max.ai.MaxApplication
import com.max.ai.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MaxOverlayService : Service() {

    private var overlayView: View? = null
    private lateinit var windowManager: WindowManager

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        startForeground()
        createOverlay()
    }

    private fun startForeground() {
        val notification = NotificationCompat.Builder(this, MaxApplication.CHANNEL_SERVICE)
            .setContentTitle("Max AI Overlay")
            .setContentText("Floating dock active")
            .setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setOngoing(true)
            .build()
        startForeground(2, notification)
    }

    private fun createOverlay() {
        val params = WindowManager.LayoutParams(
            160.dpToPx(),
            160.dpToPx(),
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 16
            y = 100
        }

        overlayView = View(this).apply {
            setBackgroundColor(0xCCFF6600.toInt())
            setOnClickListener {
                val intent = Intent(this@MaxOverlayService, com.max.ai.ui.MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }

        windowManager.addView(overlayView, params)
    }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()

    override fun onDestroy() {
        overlayView?.let { windowManager.removeView(it) }
        super.onDestroy()
    }
}
