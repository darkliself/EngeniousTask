package com.darkliself.engenioustask.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.darkliself.engenioustask.data.mapper.toUserEntity
import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import com.darkliself.engenioustask.data.room.AppRoomDataBase
import com.darkliself.engenioustask.data.room.entity.UserEntity

@OptIn(ExperimentalPagingApi::class)
class UsersRemoteMediator(
    private val apiService: UserApiService,
    private val database: AppRoomDataBase
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, UserEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id?.plus(1) ?: 1
                }
            }

            val response = apiService.getUsers().map { it.toUserEntity() }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.usersDao.nukeTable()
                }
                database.usersDao.addUsers(response)
            }

            MediatorResult.Success(endOfPaginationReached = response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}