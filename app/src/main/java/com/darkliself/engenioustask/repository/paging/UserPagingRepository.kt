package com.darkliself.engenioustask.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.darkliself.engenioustask.data.connectivity.ConnectivityDataSource
import com.darkliself.engenioustask.data.connectivity.ConnectivityManagerDataSource
import com.darkliself.engenioustask.data.paging.UserRemoteMediator
import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import com.darkliself.engenioustask.data.room.AppRoomDataBase
import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserPagingRepository @Inject constructor(
    private val apiService: UserApiService,
    private val database: AppRoomDataBase,
    private val connectivityDataSource: ConnectivityDataSource
) : UserRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getUsers(): Flow<PagingData<UserEntity>> {
        return connectivityDataSource.isOnline
            .distinctUntilChanged()
            .flatMapLatest { isConnected ->
                Pager(
                    config = PagingConfig(pageSize = PAGE_SIZE),
                    remoteMediator = if (isConnected)
                        UserRemoteMediator(apiService, database) else null,
                    pagingSourceFactory = { database.userDao.getUsersPagingSource() }
                ).flow
            }
    }

    override fun searchUsersByLogin(query: String): Flow<PagingData<UserEntity>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { if (query != "") database.userDao.getUsersByLogin(query) else database.userDao.getUsersPagingSource() }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
