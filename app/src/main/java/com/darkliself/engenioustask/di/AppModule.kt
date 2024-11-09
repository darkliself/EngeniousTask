package com.darkliself.engenioustask.di

import com.darkliself.engenioustask.data.retrofit.api.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
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