package com.darkliself.engenioustask.repository.local

import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersLocalRepository {
    fun getUsers() : Flow<List<UserEntity>>

    fun addUsers(users: List<UserEntity>)

    fun nukeTable()

    fun deleteUser(userId: Int)
}