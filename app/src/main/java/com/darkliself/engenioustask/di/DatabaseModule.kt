package com.darkliself.engenioustask.di

import android.content.Context
import androidx.room.Room
import com.darkliself.engenioustask.data.room.AppRoomDataBase
import com.darkliself.engenioustask.data.room.dao.UserDao
import com.darkliself.engenioustask.data.repository.local.UserCountRepositoryImp
import com.darkliself.engenioustask.domain.repository.UserCountRepository
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
    fun bindUsersDatabaseRepository(userCountRepositoryImp: UserCountRepositoryImp): UserCountRepository

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
        fun providePlantDao(appDatabase: AppRoomDataBase): UserDao {
            return appDatabase.userDao
        }
    }
}