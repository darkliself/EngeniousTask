package com.darkliself.engenioustask.repository

import com.darkliself.engenioustask.data.retrofit.model.User

interface UsersRemoteRepository {
    suspend fun getUsers(): List<User>
}