package com.max.ai.services
import android.accessibilityservice.AccessibilityService; import android.accessibilityservice.AccessibilityServiceInfo; import android.view.accessibility.AccessibilityEvent; import android.view.accessibility.AccessibilityNodeInfo
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint class MaxAccessibilityService : AccessibilityService() {
    override fun onServiceConnected() { super.onServiceConnected(); serviceInfo = AccessibilityServiceInfo().apply { eventTypes = AccessibilityEvent.TYPES_ALL_MASK; feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC; flags = AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS or AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS; notificationTimeout = 100 } }
    override fun onAccessibilityEvent(e: AccessibilityEvent?) {}
    override fun onInterrupt() {}
    fun readScreen(): String { val r = rootInActiveWindow ?: return ""; return extractText(r) }
    private fun extractText(n: AccessibilityNodeInfo, d: Int = 0): String { if (d > 30) return ""; val s = StringBuilder(); if (n.text?.isNotEmpty() == true) s.appendLine(n.text); for (i in 0 until n.childCount) n.getChild(i)?.let { s.append(extractText(it, d + 1)); it.recycle() }; return s.toString() }
}
