package com.darkliself.engenioustask.repository.local

import com.darkliself.engenioustask.data.room.dao.UserDao
import javax.inject.Inject

class UserDatabaseRepository @Inject constructor(
    private val userDao: UserDao,
): UserLocalRepository  {
    override fun getUsersCount() = userDao.getUsersCount()
}