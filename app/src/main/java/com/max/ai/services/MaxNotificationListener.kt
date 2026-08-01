package com.max.ai.services

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import javax.inject.Inject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@AndroidEntryPoint
class MaxNotificationListener : NotificationListenerService() {

    private val _notifications = MutableStateFlow<List<StatusBarNotification>>(emptyList())
    val notifications: StateFlow<List<StatusBarNotification>> = _notifications.asStateFlow()

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        sbn ?: return
        _notifications.value = _notifications.value + sbn
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        sbn ?: return
        _notifications.value = _notifications.value.filter { it.key != sbn.key }
    }

    fun getRecentNotifications(filter: String = ""): List<StatusBarNotification> {
        val notifs = activeNotifications ?: return emptyList()
        return if (filter.isBlank()) notifs.toList()
        else notifs.filter {
            it.notification.extras.getCharSequence("android.title")?.contains(filter, true) == true ||
            it.notification.extras.getCharSequence("android.text")?.contains(filter, true) == true
        }
    }

    fun getUnreadCount(): Int = activeNotifications?.size ?: 0
}
