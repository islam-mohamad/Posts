package com.islam.tasks.posts.presentation.uimodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostParam(
    val url: String,
    val thumbnailUrl: String,
    val posts: List<PostUiModel>
) : Parcelable