package com.darkliself.engenioustask.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login")
    val login: String,

    val id: Int,

    @SerializedName("avatar_url")
    val avatarUrl: String,

)