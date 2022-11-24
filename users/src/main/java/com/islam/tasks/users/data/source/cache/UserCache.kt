package com.islam.tasks.users.data.source.cache

import com.islam.tasks.users.domain.entity.UserEntity
import javax.inject.Inject

class UserCache @Inject constructor() {
    var usersWithPosts: List<UserEntity>? = null
}