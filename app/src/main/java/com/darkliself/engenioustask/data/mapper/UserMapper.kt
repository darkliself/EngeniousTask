package com.darkliself.engenioustask.data.mapper

import com.darkliself.engenioustask.domain.model.User
import com.darkliself.engenioustask.data.room.entity.UserEntity

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        login = login,
        avatarUrl = avatarUrl
    )
}