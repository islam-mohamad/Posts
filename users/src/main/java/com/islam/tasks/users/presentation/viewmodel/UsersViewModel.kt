package com.islam.tasks.users.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.tasks.users.domain.usecase.GetUsersUseCase
import com.islam.tasks.users.presentation.intent.UsersIntent
import com.islam.tasks.users.presentation.state.UsersViewState
import com.islam.tasks.users.presentation.uimodel.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
    ViewModel() {
    val intents = Channel<UsersIntent>(Channel.UNLIMITED)
    private val _viewState = MutableStateFlow<UsersViewState>(UsersViewState.Idle)
    val viewState: StateFlow<UsersViewState> get() = _viewState

    init {
        processIntent()
    }

    private fun processIntent() {
        viewModelScope.launch {
            intents.consumeAsFlow().collect {
                when (it) {
                    is UsersIntent.GetUsers -> {
                        _viewState.value = UsersViewState.Loading
                        reduceGetUsers()
                    }
                }
            }
        }
    }

    private fun reduceGetUsers() {
        viewModelScope.launch {
            _viewState.value = try {
                val userList = getUsersUseCase()
                UsersViewState.Success(userList.map { it.toUiModel() })
            } catch (e: Exception) {
                UsersViewState.Error(e.message)
            }
        }
    }
}