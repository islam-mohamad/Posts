package com.islam.tasks.users.data.source.remote

import com.islam.tasks.users.data.model.PostModel
import com.islam.tasks.users.data.model.UserModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    fun getUsersAsync(): Deferred<List<UserModel>>

    @GET("posts")
    fun getPostsAsync(): Deferred<List<PostModel>>
}