package com.darkliself.engenioustask.ui.screens.mainscreen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var query by rememberSaveable { mutableStateOf("") }
    val viewModel = hiltViewModel<MainScreenTestViewModel>()
    val users = viewModel.userPagingData.collectAsLazyPagingItems()
    var usersList  = viewModel.databaseIsNotEmpty.collectAsState().value
    val isOnline = viewModel.isOnline.collectAsState(true).value

    LaunchedEffect(isOnline) {
        Log.d("databaseIsNotEmpty", usersList.toString())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.searchUserByLogin(it)
            },
            enabled = usersList,
            placeholder = {
                if (!isOnline && !usersList) {
                    Text("Search will not work")
                }
            }
        )

        LazyColumn() {
            items(users.itemCount) {
                Log.e("ItemCount", "${users.itemCount}")
                Text(text = users[it]!!.login)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}