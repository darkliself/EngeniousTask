package com.darkliself.engenioustask.repository.remote

import com.darkliself.engenioustask.data.retrofit.model.User

interface UsersRemoteRepository {
    suspend fun getUsers(): List<User>
}