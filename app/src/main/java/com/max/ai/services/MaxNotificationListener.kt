package com.max.ai.services
import android.service.notification.NotificationListenerService; import android.service.notification.StatusBarNotification
import kotlinx.coroutines.flow.MutableStateFlow; import kotlinx.coroutines.flow.StateFlow; import kotlinx.coroutines.flow.asStateFlow
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint class MaxNotificationListener : NotificationListenerService() {
    private val _n = MutableStateFlow<List<StatusBarNotification>>(emptyList())
    val notifications: StateFlow<List<StatusBarNotification>> = _n.asStateFlow()
    override fun onNotificationPosted(sbn: StatusBarNotification?) { sbn?.let { _n.value = _n.value + it } }
    override fun onNotificationRemoved(sbn: StatusBarNotification?) { sbn?.let { _n.value = _n.value.filter { n -> n.key != it.key } } }
}
