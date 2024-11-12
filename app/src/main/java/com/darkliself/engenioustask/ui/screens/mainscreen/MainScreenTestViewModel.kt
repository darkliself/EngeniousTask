package com.darkliself.engenioustask.ui.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.darkliself.engenioustask.data.room.entity.UserEntity
import com.darkliself.engenioustask.repository.paging.UsersPagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainScreenTestViewModel @Inject constructor(
    repository: UsersPagingRepository
): ViewModel() {
    val usersData: Flow<PagingData<UserEntity>> = repository.getUsers()
        .cachedIn(viewModelScope)
}