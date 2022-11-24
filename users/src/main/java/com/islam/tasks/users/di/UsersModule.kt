package com.islam.tasks.users.di

import com.islam.tasks.users.data.repository.UserRepositoryImpl
import com.islam.tasks.users.data.source.remote.UserApi
import com.islam.tasks.users.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
abstract class UsersModule {
    @Binds
    abstract fun bindUsersRepository(impl: UserRepositoryImpl): UserRepository

    companion object {
        @Provides
        fun provideUsersApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)
    }
}