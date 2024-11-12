package com.darkliself.engenioustask.repository.local

import kotlinx.coroutines.flow.Flow

interface UserLocalRepository {
    fun getUsersCount() : Flow<Int>
}