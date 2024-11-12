package com.darkliself.engenioustask.di

import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import com.darkliself.engenioustask.repository.paging.UserPagingRepository
import com.darkliself.engenioustask.repository.paging.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bind(userPagingRepository: UserPagingRepository): UserRepository

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        @Provides
        @Singleton
        fun provideApi(builder: Retrofit.Builder): UserApiService {
            return builder.build().create(UserApiService::class.java)
        }

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit.Builder {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        }
    }
}