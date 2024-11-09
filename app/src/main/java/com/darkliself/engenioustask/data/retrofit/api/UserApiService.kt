package com.darkliself.engenioustask.data.retrofit.api

import com.darkliself.engenioustask.data.retrofit.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}