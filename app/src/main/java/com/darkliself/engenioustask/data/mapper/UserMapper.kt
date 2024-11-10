package com.darkliself.engenioustask.data.mapper

import com.darkliself.engenioustask.data.retrofit.model.User
import com.darkliself.engenioustask.data.room.entity.UserEntity

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        login = login,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        userViewType = userViewType,
        siteAdmin = siteAdmin
    )
}