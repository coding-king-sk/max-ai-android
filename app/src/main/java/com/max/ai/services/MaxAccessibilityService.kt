package com.max.ai.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.graphics.Rect
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MaxAccessibilityService : AccessibilityService() {

    @Inject lateinit var screenReader: com.max.ai.core.screen.ScreenReader

    override fun onServiceConnected() {
        super.onServiceConnected()
        val info = AccessibilityServiceInfo().apply {
            eventTypes = AccessibilityEvent.TYPES_ALL_MASK
            feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC
            flags = AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS or
                    AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS
            notificationTimeout = 100
        }
        serviceInfo = info
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        event ?: return
        screenReader.handleEvent(event)
    }

    override fun onInterrupt() {}

    fun readScreen(): String {
        val rootNode = rootInActiveWindow ?: return ""
        return extractText(rootNode)
    }

    private fun extractText(node: AccessibilityNodeInfo, depth: Int = 0): String {
        if (depth > 30) return ""
        val sb = StringBuilder()
        if (node.text?.isNotEmpty() == true) {
            sb.appendLine(node.text)
        }
        if (node.contentDescription?.isNotEmpty() == true) {
            sb.appendLine("[${node.contentDescription}]")
        }
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { child ->
                sb.append(extractText(child, depth + 1))
                child.recycle()
            }
        }
        return sb.toString()
    }

    fun findAndClick(text: String): Boolean {
        val nodes = findNodesByText(text)
        nodes.forEach { node ->
            if (node.isClickable) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                return true
            }
        }
        return false
    }

    fun findAndType(fieldText: String, text: String): Boolean {
        val nodes = findEditableNodes()
        nodes.forEach { node ->
            if (node.text?.contains(fieldText) == true || node.hintText?.contains(fieldText) == true) {
                val args = Bundle().apply {
                    putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text)
                }
                node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args)
                return true
            }
        }
        return false
    }

    private fun findNodesByText(search: String): List<AccessibilityNodeInfo> {
        val result = mutableListOf<AccessibilityNodeInfo>()
        val rootNode = rootInActiveWindow ?: return result
        searchNodes(rootNode, search, result)
        return result
    }

    private fun searchNodes(node: AccessibilityNodeInfo, search: String, result: MutableList<AccessibilityNodeInfo>) {
        if (node.text?.contains(search, ignoreCase = true) == true ||
            node.contentDescription?.contains(search, ignoreCase = true) == true) {
            result.add(node)
        }
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { searchNodes(it, search, result) }
        }
    }

    private fun findEditableNodes(): List<AccessibilityNodeInfo> {
        val result = mutableListOf<AccessibilityNodeInfo>()
        val rootNode = rootInActiveWindow ?: return result
        findEditable(rootNode, result)
        return result
    }

    private fun findEditable(node: AccessibilityNodeInfo, result: MutableList<AccessibilityNodeInfo>) {
        if (node.isEditable) result.add(node)
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { findEditable(it, result) }
        }
    }
}
