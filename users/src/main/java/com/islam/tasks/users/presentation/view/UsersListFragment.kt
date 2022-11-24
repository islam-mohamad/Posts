package com.islam.tasks.users.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.islam.tasks.core.makeGone
import com.islam.tasks.core.makeVisible
import com.islam.tasks.users.R
import com.islam.tasks.users.databinding.FragmentUsersListBinding
import com.islam.tasks.users.presentation.intent.UsersIntent
import com.islam.tasks.users.presentation.state.UsersViewState
import com.islam.tasks.users.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersListFragment : Fragment() {

    private lateinit var binding: FragmentUsersListBinding

    private val viewModel by viewModels<UsersViewModel>()

    private val adapter: UsersAdapter by lazy {
        UsersAdapter {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        render()
        initActions()
    }

    private fun initViews() {
        binding.userListRv.adapter = adapter
    }

    private fun initActions() {
        lifecycleScope.launchWhenStarted {
            viewModel.intents.send(UsersIntent.GetUsers)
        }
    }

    private fun render() {
        lifecycleScope.launchWhenStarted {
            viewModel.viewState.collect {
                when (it) {
                    is UsersViewState.Loading -> binding.loadingPb.makeVisible()
                    is UsersViewState.Success -> {
                        binding.loadingPb.makeGone()
                        adapter.submitList(it.usersList)
                    }
                    is UsersViewState.Error -> {
                        binding.loadingPb.makeGone()
                        Toast.makeText(
                            context,
                            it.error ?: getString(R.string.something_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    UsersViewState.Idle -> {

                    }
                }
            }
        }
    }
}