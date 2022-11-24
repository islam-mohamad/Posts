package com.islam.tasks.users

import com.islam.tasks.core.test
import com.islam.tasks.users.domain.usecase.GetUsersUseCase
import com.islam.tasks.users.presentation.intent.UsersIntent
import com.islam.tasks.users.presentation.state.UsersViewState
import com.islam.tasks.users.presentation.viewmodel.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {
    @Mock
    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var viewModel: UsersViewModel
    private val testScheduler = TestCoroutineScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        viewModel = UsersViewModel(getUsersUseCase = getUsersUseCase)
    }

    @Test
    fun `give GetUsers intent,  getUsersUseCase should be invoked and return success`() = runTest {
        Mockito.`when`(getUsersUseCase.invoke()).thenReturn(emptyList())
        val observer = viewModel.viewState.test(this)
        viewModel.intents.send(UsersIntent.GetUsers)

        observer.assertValuesAndFinish(
            UsersViewState.Idle,
            UsersViewState.Loading,
            UsersViewState.Success(emptyList())
        )
        observer.finish()
    }

    @Test
    fun `give GetUsers intent,  and getUsersUseCase throws an exception it should return Error`() =
        runTest {
            Mockito.`when`(getUsersUseCase.invoke()).thenThrow(RuntimeException(ERROR_MESSAGE))
            val observer = viewModel.viewState.test(this)
            viewModel.intents.send(UsersIntent.GetUsers)

            observer.assertValuesAndFinish(
                UsersViewState.Idle,
                UsersViewState.Loading,
                UsersViewState.Error(ERROR_MESSAGE)
            )
            observer.finish()
        }

    companion object {
        const val ERROR_MESSAGE = "ERROR_MESSAGE"
    }
}