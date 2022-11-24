package com.islam.tasks.floawrd.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.islam.tasks.floawrd.R
import com.islam.tasks.posts.PostsFragment
import com.islam.tasks.users.presentation.view.UsersListNav
import javax.inject.Inject

class UsersListNavigation @Inject constructor() : UsersListNav {
    override fun navigateToPosts(activity: FragmentActivity?, userId: Int) {
        activity?.run {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container_view,
                    PostsFragment.newInstance(userId)
                )
                addToBackStack(null)
            }
        }
    }
}