package com.islam.tasks.users.data.model

import com.google.gson.annotations.SerializedName


data class UserModel(
    @SerializedName("albumId") var albumId: Int? = null,
    @SerializedName("userId") var userId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("thumbnailUrl") var thumbnailUrl: String? = null
)