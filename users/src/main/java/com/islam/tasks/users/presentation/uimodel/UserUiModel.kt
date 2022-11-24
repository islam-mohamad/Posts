package com.islam.tasks.users.presentation.uimodel

import com.islam.tasks.users.domain.entity.PostEntity

data class UserUiModel(
    val id: Int,
    val name: String,
    val url: String,
    val thumbnailUrl: String,
    val posts: List<PostEntity>,
    val postsCount: Int
)