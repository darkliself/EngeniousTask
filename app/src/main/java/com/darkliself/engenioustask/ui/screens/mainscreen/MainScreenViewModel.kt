package com.darkliself.engenioustask.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.darkliself.engenioustask.data.room.entity.UserEntity
import com.darkliself.engenioustask.domain.ConnectionStateUseCase
import com.darkliself.engenioustask.repository.local.UserLocalRepository
import com.darkliself.engenioustask.repository.paging.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val userPagingRepository: UserRepository,
    private val userLocalRepository: UserLocalRepository,
    connectionStateUseCase: ConnectionStateUseCase
) : ViewModel() {

    private val mDatabaseIsNotEmpty = MutableStateFlow(false)
    val databaseIsNotEmpty: StateFlow<Boolean> = mDatabaseIsNotEmpty
    val isOnline = connectionStateUseCase()
    var userPagingData: Flow<PagingData<UserEntity>> = userPagingRepository.getUsers()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            userLocalRepository.getUsersCount()
                .collect { users ->
                    mDatabaseIsNotEmpty.value = users > 0
                }
        }
    }

    fun searchUserByLogin(query: String) {
        userPagingData = userPagingRepository.searchUsersByLogin(query).cachedIn(viewModelScope)
    }
}