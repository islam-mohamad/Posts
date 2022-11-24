package com.islam.tasks.posts.domain.usecase

import com.islam.tasks.posts.domain.entity.PostEntity
import com.islam.tasks.posts.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostsRepository) {
    suspend operator fun invoke(param: Int): List<PostEntity> {
        return repository.getPosts(param)
    }
}