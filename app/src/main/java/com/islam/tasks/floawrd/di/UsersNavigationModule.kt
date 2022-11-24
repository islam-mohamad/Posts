package com.islam.tasks.floawrd.di

import com.islam.tasks.floawrd.navigation.UsersListNavigation
import com.islam.tasks.users.presentation.view.UsersListNav
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class UsersNavigationModule {
    @Binds
    abstract fun provideUsersNavigation(impl: UsersListNavigation): UsersListNav
}