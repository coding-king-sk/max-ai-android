package com.max.ai.services
import android.app.Service; import android.content.Context; import android.content.Intent; import android.graphics.PixelFormat; import android.os.Build; import android.os.IBinder; import android.view.Gravity; import android.view.View; import android.view.WindowManager
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint class MaxOverlayService : Service() {
    private var v: View? = null; private lateinit var wm: WindowManager
    override fun onBind(i: Intent?) = null
    override fun onCreate() {
        super.onCreate(); wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = WindowManager.LayoutParams(160.toDp(), 160.toDp(), if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT).apply { gravity = Gravity.TOP or Gravity.END; x = 16; y = 100 }
        v = View(this).apply { setBackgroundColor(0xCCFF6600.toInt()); setOnClickListener { startActivity(Intent(this@MaxOverlayService, com.max.ai.ui.MainActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }) } }
        wm.addView(v, p)
    }
    private fun Int.toDp() = (this * resources.displayMetrics.density).toInt()
    override fun onDestroy() { v?.let { wm.removeView(it) }; super.onDestroy() }
}
