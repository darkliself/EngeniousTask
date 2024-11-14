package com.darkliself.engenioustask.data.repository.local

import com.darkliself.engenioustask.data.room.dao.UserDao
import com.darkliself.engenioustask.domain.repository.UserCountRepository
import javax.inject.Inject

class UserCountRepositoryImp @Inject constructor(
    private val userDao: UserDao,
): UserCountRepository {
    override fun getUsersCount() = userDao.getUsersCount()
}