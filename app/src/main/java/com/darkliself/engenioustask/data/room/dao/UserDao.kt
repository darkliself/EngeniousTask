package com.darkliself.engenioustask.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT COUNT(*) FROM user_table")
    fun getUsersCount(): Flow<Int>

    @Query("SELECT * FROM user_table")
    fun getUsersPagingSource(): PagingSource<Int, UserEntity>

    @Query("SELECT * FROM user_table WHERE login LIKE '%' || :login || '%' ORDER BY login ASC")
    fun getUsersByLogin(login: String): PagingSource<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(episode: List<UserEntity>)

    @Query("DELETE FROM user_table")
    fun nukeTable()
}
