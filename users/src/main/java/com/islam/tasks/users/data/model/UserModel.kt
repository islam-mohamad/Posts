package com.islam.tasks.users.data.model

import com.google.gson.annotations.SerializedName


data class UserModel(
    @SerializedName("albumId") var albumId: Int,
    @SerializedName("userId") var userId: Int,
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
    @SerializedName("thumbnailUrl") var thumbnailUrl: String
)