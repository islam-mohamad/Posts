package com.islam.tasks.users.domain.usecase

import com.islam.tasks.users.domain.entity.UserEntity
import com.islam.tasks.users.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private var repository: UserRepository) {
    suspend operator fun invoke(): List<UserEntity> {
        return repository.getUsers()
    }
}