package com.islam.tasks.posts.domain.repository

import com.islam.tasks.posts.domain.entity.PostEntity

interface PostsRepository {
    suspend fun getPosts(userId: Int): List<PostEntity>
}