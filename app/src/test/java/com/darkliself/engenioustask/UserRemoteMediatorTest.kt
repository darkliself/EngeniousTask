package com.darkliself.engenioustask

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.darkliself.engenioustask.data.paging.UserRemoteMediator
import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import com.darkliself.engenioustask.data.room.AppRoomDataBase
import com.darkliself.engenioustask.data.room.entity.UserEntity
import com.darkliself.engenioustask.domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
@RunWith(AndroidJUnit4::class)
@Config(minSdk = Config.OLDEST_SDK, maxSdk = Config.TARGET_SDK)
class UserRemoteMediatorTest {

    private lateinit var database: AppRoomDataBase
    private val mockApiService = mockk<UserApiService>(relaxed = true)
    private lateinit var remoteMediator: UserRemoteMediator

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppRoomDataBase::class.java
        ).allowMainThreadQueries().build()

        remoteMediator = UserRemoteMediator(mockApiService, database)
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun refreshLoad_success() = runTest {
        val fakeResponse = listOf(
            User(id = 1, login = "User1", avatarUrl = "url1"),
            User(id = 2, login = "User2", avatarUrl = "url2")
        )
        coEvery  { mockApiService.getUsers() } returns fakeResponse
        val pagingState = PagingState<Int, UserEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assert(result is RemoteMediator.MediatorResult.Success)
        val users = database.userDao.getUsersCount()
        assertEquals(2, users.first())
    }

    @Test
    fun prependLoad_endOfPaginationReached() = runBlocking {
        val pagingState = PagingState<Int, UserEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(20),
            leadingPlaceholderCount = 0
        )
        val result = remoteMediator.load(LoadType.PREPEND, pagingState)
        assert(result is RemoteMediator.MediatorResult.Success)
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun loadWithApiError() = runBlocking {
        coEvery  { mockApiService.getUsers() } throws IOException("Network error")
        val pagingState = PagingState<Int, UserEntity>(
            pages = listOf(),
            anchorPosition = null,
            config = PagingConfig(10),
            leadingPlaceholderCount = 0
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        assert(result is RemoteMediator.MediatorResult.Error)
    }
}