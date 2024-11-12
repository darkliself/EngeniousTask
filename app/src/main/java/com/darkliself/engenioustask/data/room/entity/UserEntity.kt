package com.darkliself.engenioustask.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val login: String,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
)