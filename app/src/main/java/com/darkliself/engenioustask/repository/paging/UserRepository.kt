package com.darkliself.engenioustask.repository.paging

import androidx.paging.PagingData
import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<PagingData<UserEntity>>
    fun searchUsersByLogin(query: String): Flow<PagingData<UserEntity>>
}