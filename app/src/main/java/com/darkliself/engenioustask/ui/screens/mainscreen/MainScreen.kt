package com.darkliself.engenioustask.ui.screens.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.darkliself.engenioustask.core.ConstantSize
import com.darkliself.engenioustask.core.ConstantText
import com.darkliself.engenioustask.ui.screens.mainscreen.components.UserCard
import com.darkliself.engenioustask.ui.theme.darkGreen
import com.darkliself.engenioustask.ui.theme.mediumGreen
import com.darkliself.engenioustask.ui.theme.mintGreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var query by rememberSaveable { mutableStateOf("") }
    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()
    val users = mainScreenViewModel.userPagingData.collectAsLazyPagingItems()
    val usersList = mainScreenViewModel.databaseIsNotEmpty.collectAsState().value
    val isOnline = mainScreenViewModel.isOnline.collectAsState(true).value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(mediumGreen, darkGreen)
                )
            )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(ConstantSize.PADDING_DEFAULT)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it.trim()
                    mainScreenViewModel.searchUserByLogin(query)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = usersList,
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    if (!isOnline && !usersList) {
                        Text(ConstantText.SEARCH_DISABLE)
                    }
                }
            )

            Spacer(modifier = Modifier.height(ConstantSize.MEDIUM_SPACER_HEIGHT))

            LazyColumn {
                items(users.itemCount) {
                    users[it]?.let { elem ->
                        UserCard(
                            elem, modifier = Modifier
                                .fillMaxWidth()
                                .height(height = ConstantSize.USER_CARD_HEIGHT)
                                .clip(RoundedCornerShape(ConstantSize.CORNER_RADIUS_MEDIUM)),
                            shape = RoundedCornerShape(ConstantSize.CORNER_RADIUS_MEDIUM),
                            color = mintGreen
                        )
                        Spacer(modifier = Modifier.height(ConstantSize.CARD_SPACER))
                    }
                }
            }
        }
    }
}
