package com.darkliself.engenioustask.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserCountRepository {
    fun getUsersCount() : Flow<Int>
}