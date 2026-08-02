package com.max.ai.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton
import dagger.hilt.android.qualifiers.ApplicationContext

@Singleton
class ConnectivityMonitor @Inject constructor(@ApplicationContext private val ctx: Context) {
    private val _online = MutableStateFlow(true)
    val online: StateFlow<Boolean> = _online.asStateFlow()
    private val cb = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(n: Network) { _online.value = true }
        override fun onLost(n: Network) { _online.value = false }
    }
    fun start() { (ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).registerNetworkCallback(NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build(), cb) }
    fun stop() { (ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).unregisterNetworkCallback(cb) }
}
