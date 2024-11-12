package com.darkliself.engenioustask.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.darkliself.engenioustask.data.room.entity.UserEntity
import com.darkliself.engenioustask.domain.ConnectionStateUseCase
import com.darkliself.engenioustask.repository.local.UsersLocalRepository
import com.darkliself.engenioustask.repository.paging.UsersPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val usersPagingRepository: UsersPagingRepository,
    private val usersLocalRepository: UsersLocalRepository,
    connectionStateUseCase: ConnectionStateUseCase
) : ViewModel() {

    private val mDatabaseIsNotEmpty = MutableStateFlow(false)
    val databaseIsNotEmpty: StateFlow<Boolean> = mDatabaseIsNotEmpty
    val isOnline = connectionStateUseCase()
    var userPagingData: Flow<PagingData<UserEntity>> = usersPagingRepository.getUsers()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            usersLocalRepository.getUsersCount()
                .collect { users ->
                    mDatabaseIsNotEmpty.value = users > 0
                }
        }
    }

    fun searchUserByLogin(query: String) {
        userPagingData = usersPagingRepository.searchUsersByLogin(query).cachedIn(viewModelScope)
    }
}