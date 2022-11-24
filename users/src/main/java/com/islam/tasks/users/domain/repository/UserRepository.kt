package com.islam.tasks.users.domain.repository

import com.islam.tasks.users.domain.entity.UserEntity

interface UserRepository {
    suspend fun getUsers(): List<UserEntity>
}