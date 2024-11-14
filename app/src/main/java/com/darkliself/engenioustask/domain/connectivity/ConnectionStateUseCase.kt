package com.darkliself.engenioustask.domain.connectivity

import com.darkliself.engenioustask.data.connectivity.ConnectivityManagerDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectionStateUseCase @Inject constructor(
    private val connectivityManagerDataSource: ConnectivityManagerDataSource,
) {
    operator fun invoke(): Flow<Boolean> {
        return connectivityManagerDataSource.isOnline
    }
}