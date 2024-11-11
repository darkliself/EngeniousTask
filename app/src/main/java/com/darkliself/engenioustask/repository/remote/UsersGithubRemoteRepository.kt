package com.darkliself.engenioustask.repository.remote

import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import javax.inject.Inject

class UsersGithubRemoteRepository @Inject constructor(
    private val rickAndMortyApiService: UserApiService,
) : UsersRemoteRepository {
    override suspend fun getUsers() = rickAndMortyApiService.getUsers()
}