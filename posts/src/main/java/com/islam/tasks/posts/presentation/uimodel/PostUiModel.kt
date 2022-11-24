package com.islam.tasks.posts.presentation.uimodel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostUiModel(var id: Int, var title: String, var body: String) :
    Parcelable