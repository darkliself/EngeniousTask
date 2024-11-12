package com.darkliself.engenioustask.di

import android.content.Context
import android.net.ConnectivityManager
import com.darkliself.engenioustask.data.connectivity.ConnectivityDataSource
import com.darkliself.engenioustask.data.connectivity.ConnectivityManagerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@Suppress("kotlin:S6517")
interface ConnectivityModule {
    @Binds
    fun bindConnectivityManagerDataSource(connectivityManagerDataSource: ConnectivityManagerDataSource): ConnectivityDataSource

    companion object {
        @Provides
        @Singleton
        fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
            context.getSystemService(ConnectivityManager::class.java)

    }
}
