package com.islam.tasks.users.domain.entity


data class UserEntity(
    val albumId: Int,
    val userId: Int,
    val name: String,
    val url: String,
    val thumbnailUrl: String,
    val posts: List<PostEntity>
)