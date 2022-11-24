package com.islam.tasks.users.presentation.state

import com.islam.tasks.users.presentation.uimodel.UserUiModel

sealed class UsersViewState {
    object Idle : UsersViewState()
    object Loading : UsersViewState()
    data class Success(val usersList: List<UserUiModel>) : UsersViewState()
    data class Error(val error: String) : UsersViewState()
}