package com.darkliself.engenioustask.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val login: String,

    @ColumnInfo(name = "node_id")
    val nodeId: String,

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "gravatar_id")
    val gravatarId: String,

    val url: String,

    @ColumnInfo(name = "html_url")
    val htmlUrl: String,

    @ColumnInfo(name = "followers_url")
    val followersUrl: String,

    @ColumnInfo(name = "following_url")
    val followingUrl: String,

    @ColumnInfo(name = "gists_url")
    val gistsUrl: String,

    @ColumnInfo(name = "starred_url")
    val starredUrl: String,

    @ColumnInfo(name = "subscriptions_url")
    val subscriptionsUrl: String,

    @ColumnInfo(name = "organizations_url")
    val organizationsUrl: String,

    @ColumnInfo(name = "repos_url")
    val reposUrl: String,

    @ColumnInfo(name = "events_url")
    val eventsUrl: String,

    @ColumnInfo(name = "received_events_url")
    val receivedEventsUrl: String,

    val type: String,

    @ColumnInfo(name = "user_view_type")
    val userViewType: String,

    @ColumnInfo(name = "site_admin")
    val siteAdmin: Boolean
)