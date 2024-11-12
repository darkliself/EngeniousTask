package com.darkliself.engenioustask.repository.local

import com.darkliself.engenioustask.data.room.dao.UsersDao
import javax.inject.Inject

class UsersDatabaseRepository @Inject constructor(
    private val usersDao: UsersDao,
): UsersLocalRepository  {
    override fun getUsersCount() = usersDao.getUsersCount()
}