package com.darkliself.engenioustask.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.darkliself.engenioustask.data.paging.UsersRemoteMediator
import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import com.darkliself.engenioustask.data.room.AppRoomDataBase
import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UsersPagingRepository @Inject constructor(
    private val apiService: UserApiService,
    private val database: AppRoomDataBase
) {
        fun getUsers(): Flow<PagingData<UserEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = UsersRemoteMediator(apiService, database),
            pagingSourceFactory = { database.usersDao.getUsersPagingSource() }
        ).flow
    }
}