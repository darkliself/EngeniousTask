package com.darkliself.engenioustask.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.darkliself.engenioustask.data.connectivity.ConnectivityManagerDataSource
import com.darkliself.engenioustask.data.paging.UsersRemoteMediator
import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import com.darkliself.engenioustask.data.room.AppRoomDataBase
import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UsersPagingRepository @Inject constructor(
    private val apiService: UserApiService,
    private val database: AppRoomDataBase,
    private val connectivityDataSource: ConnectivityManagerDataSource
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUsers(): Flow<PagingData<UserEntity>> {
        return connectivityDataSource.isActive
            .distinctUntilChanged()
            .flatMapLatest { isConnected ->
                Pager(
                    config = PagingConfig(pageSize = 20),
                    remoteMediator = if (isConnected)
                        UsersRemoteMediator(apiService, database) else null,
                    pagingSourceFactory = { database.usersDao.getUsersPagingSource() }
                ).flow
            }
    }
}
