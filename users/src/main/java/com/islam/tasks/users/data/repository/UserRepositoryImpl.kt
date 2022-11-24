package com.islam.tasks.users.data.repository

import com.islam.tasks.users.data.model.PostModel
import com.islam.tasks.users.data.model.UserModel
import com.islam.tasks.users.data.model.toEntity
import com.islam.tasks.users.data.source.remote.UserApi
import com.islam.tasks.users.domain.entity.PostEntity
import com.islam.tasks.users.domain.entity.UserEntity
import com.islam.tasks.users.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private var api: UserApi) : UserRepository {
    override suspend fun getUsers(): List<UserEntity> {
        val users = api.getUsersAsync()
        val usersPosts = api.getPostsAsync()
        return merge(users.await(), usersPosts.await())
    }

    private fun merge(users: List<UserModel>, posts: List<PostModel>): List<UserEntity> {
        posts.sortedBy { it.userId }
        var index = 0
        return users.map { user ->
            val userPosts = mutableListOf<PostEntity>()
            while (index < posts.size) {
                if (posts[index].userId == user.userId) {
                    userPosts.add(posts[index].toEntity())
                } else {
                    break
                }
                index++
            }
            user.toEntity(userPosts)
        }
    }
}
