package com.islam.tasks.users.data.model

import com.islam.tasks.users.domain.entity.PostEntity
import com.islam.tasks.users.domain.entity.UserEntity

fun UserModel.toEntity(posts: List<PostEntity>) = UserEntity(
    albumId = albumId,
    userId = userId,
    name = name,
    url = url,
    thumbnailUrl = thumbnailUrl,
    posts = posts
)

fun PostModel.toEntity() = PostEntity(userId = userId, id = id, title = title, body = body)