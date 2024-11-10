package com.darkliself.engenioustask.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM user_table")
    fun getUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(episode: List<UserEntity>)

    @Query("DELETE FROM user_table WHERE id = :userId")
    fun deleteUserById(userId: Int)

    @Query("DELETE FROM user_table")
    fun nukeTable()
}
