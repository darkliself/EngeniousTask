package com.darkliself.engenioustask.di

import android.content.Context
import androidx.room.Room
import com.darkliself.engenioustask.data.room.AppRoomDataBase
import com.darkliself.engenioustask.data.room.dao.UsersDao
import com.darkliself.engenioustask.repository.local.UsersDatabaseRepository
import com.darkliself.engenioustask.repository.local.UsersLocalRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DatabaseModule {

    @Binds
    fun bindUsersDatabaseRepository(usersDatabaseRepository: UsersDatabaseRepository): UsersLocalRepository

    companion object {
        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): AppRoomDataBase {
            return Room.databaseBuilder(
                appContext,
                AppRoomDataBase::class.java,
                "engenious_db"
            ).build()
        }

        @Provides
        fun providePlantDao(appDatabase: AppRoomDataBase): UsersDao {
            return appDatabase.usersDao
        }
    }
}