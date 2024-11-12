package com.darkliself.engenioustask.domain

import com.darkliself.engenioustask.data.connectivity.ConnectivityDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectionStateUseCase @Inject constructor(
    private val connectivityDataSource: ConnectivityDataSource,
) {
    operator fun invoke(): Flow<Boolean> {
        return connectivityDataSource.isActive
    }
}