package com.darkliself.engenioustask

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.darkliself.engenioustask.data.connectivity.ConnectivityManagerDataSource
import com.darkliself.engenioustask.ui.screens.mainscreen.MainScreenTestViewModel
import com.darkliself.engenioustask.ui.theme.EngeniousTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var connectivityManagerDataSource: ConnectivityManagerDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        CoroutineScope(Dispatchers.IO).launch {
//            connectivityManagerDataSource.isActive.collect {
//                Log.d("ConnectivityManagerDataSource", "state is $it")
//            }
//        }

        enableEdgeToEdge()
        setContent {
            EngeniousTaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<MainScreenTestViewModel>()
    val users = viewModel.usersData.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize().zIndex(1f)) {
        Text(
            text = "Hello $name!",
            modifier = modifier
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EngeniousTaskTheme {
        Greeting("Android")
    }
}