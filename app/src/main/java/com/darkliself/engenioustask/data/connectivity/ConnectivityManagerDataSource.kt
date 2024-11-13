package com.darkliself.engenioustask.data.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityManagerDataSource {
    val isOnline: Flow<Boolean>
}
