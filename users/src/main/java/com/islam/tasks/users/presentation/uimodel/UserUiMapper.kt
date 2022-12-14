package com.islam.tasks.users.presentation.uimodel

import com.islam.tasks.users.domain.entity.UserEntity

fun UserEntity.toUiModel() =
    UserUiModel(
        id = userId,
        name = name,
        url = url,
        thumbnailUrl = thumbnailUrl,
        posts = posts,
        postsCount = posts.size)