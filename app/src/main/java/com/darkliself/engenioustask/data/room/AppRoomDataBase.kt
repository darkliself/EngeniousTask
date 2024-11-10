package com.darkliself.engenioustask.data.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.darkliself.engenioustask.data.room.dao.UsersDao
import com.darkliself.engenioustask.data.room.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    exportSchema = true,
    version = 2
)

abstract class AppRoomDataBase : RoomDatabase() {
    abstract val usersDao: UsersDao
}