package com.islam.tasks.users.data.repository

import android.util.Log
import com.google.gson.Gson
import com.islam.tasks.users.data.model.PostModel
import com.islam.tasks.users.data.model.UserModel
import com.islam.tasks.users.data.model.toEntity
import com.islam.tasks.users.data.source.cache.UserCache
import com.islam.tasks.users.data.source.remote.UserApi
import com.islam.tasks.users.domain.entity.PostEntity
import com.islam.tasks.users.domain.entity.UserEntity
import com.islam.tasks.users.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private var api: UserApi,
    private val userCache: UserCache,
    private val gson: Gson
) : UserRepository {
    override suspend fun getUsers(): List<UserEntity> {
        return try {
            val users = api.getUsersAsync()
            val usersPosts = api.getPostsAsync()
            val usersWithPosts = merge(users.await(), usersPosts.await())
            CoroutineScope(Dispatchers.IO).launch {
                userCache.saveUsers(usersWithPosts)
            }
            usersWithPosts
        } catch (e: IOException) {
            val usersWithPosts =
                gson.fromJson(userCache.getUsers().getOrNull(), Array<UserEntity>::class.java)
                    .asList()
            usersWithPosts
        }
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
