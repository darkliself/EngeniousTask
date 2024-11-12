package com.darkliself.engenioustask.data.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityDataSource {
    val isOnline: Flow<Boolean>
}
