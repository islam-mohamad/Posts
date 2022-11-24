package com.islam.tasks.users.presentation.view

import androidx.fragment.app.FragmentActivity
import com.islam.tasks.users.presentation.uimodel.UserUiModel

interface UsersListNav {
    fun navigateToPosts(activity: FragmentActivity?, user: UserUiModel)
}