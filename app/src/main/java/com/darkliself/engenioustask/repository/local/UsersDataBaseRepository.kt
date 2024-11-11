package com.darkliself.engenioustask.repository.local

import com.darkliself.engenioustask.data.room.dao.UsersDao
import com.darkliself.engenioustask.data.room.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersDataBaseRepository @Inject constructor(
    private val usersDao: UsersDao
) : UsersLocalRepository {
    override fun getUsers(): Flow<List<UserEntity>> {
        return usersDao.getUsers()
    }

    override fun addUsers(users: List<UserEntity>) {
        usersDao.addUsers(users)
    }

    override fun nukeTable() {
        usersDao.nukeTable()
    }

    override fun deleteUser(userId: Int) {
        usersDao.deleteUserById(userId)
    }
}