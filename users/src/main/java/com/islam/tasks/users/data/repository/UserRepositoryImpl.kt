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
        val asd = merge(users.await(), usersPosts.await())
        return asd
    }

    private fun merge(users: List<UserModel>, posts: List<PostModel>): List<UserEntity> {
        val sortPosts = posts.sortedBy { it.userId }
        var index = 0
        return users.map { user ->
            val userPosts = mutableListOf<PostEntity>()
            while (index < sortPosts.size) {
                if (sortPosts[index].userId == user.userId) {
                    userPosts.add(sortPosts[index].toEntity())
                } else {
                    break
                }
                index++
            }
            user.toEntity(userPosts)
        }
    }
}
