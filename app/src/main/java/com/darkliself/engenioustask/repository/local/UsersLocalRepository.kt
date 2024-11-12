package com.darkliself.engenioustask.repository.local

import kotlinx.coroutines.flow.Flow

interface UsersLocalRepository {
    fun getUsersCount() : Flow<Int>
}