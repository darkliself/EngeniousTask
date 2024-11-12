package com.darkliself.engenioustask.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darkliself.engenioustask.data.room.dao.UserDao
import com.darkliself.engenioustask.data.room.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    exportSchema = true,
    version = 1
)

abstract class AppRoomDataBase : RoomDatabase() {
    abstract val userDao: UserDao
}