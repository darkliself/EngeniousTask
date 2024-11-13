package com.darkliself.engenioustask.data.retrofit.api

import com.darkliself.engenioustask.domain.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}