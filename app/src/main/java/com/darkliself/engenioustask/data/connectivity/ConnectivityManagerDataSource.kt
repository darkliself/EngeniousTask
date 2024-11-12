package com.darkliself.engenioustask.data.connectivity

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

class ConnectivityManagerDataSource @Inject constructor(
    private val connectivityManager: ConnectivityManager,
) : ConnectivityDataSource {

    override val isOnline: Flow<Boolean> = callbackFlow {

        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                trySend(true)
                Log.d(TAG, "Connection is available")
            }

            override fun onLost(network: Network) {
                Log.d(TAG, "Connection is lost")
                trySend(false)
            }
        }

        trySend(connectivityManager.isCurrentlyConnected())

        connectivityManager.registerDefaultNetworkCallback(callback)
        Log.d(TAG, "Register NetworkCallback")

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
            Log.d(TAG, "Unregister NetworkCallback")
        }
    }.conflate()

    companion object {
        private val TAG: String = ConnectivityManagerDataSource::class.java.simpleName

        fun ConnectivityManager.isCurrentlyConnected() =
            activeNetwork
                ?.let(::getNetworkCapabilities)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
    }
}