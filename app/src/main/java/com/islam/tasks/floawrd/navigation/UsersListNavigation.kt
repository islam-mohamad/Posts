package com.islam.tasks.floawrd.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.islam.tasks.floawrd.R
import com.islam.tasks.posts.presentation.uimodel.PostParam
import com.islam.tasks.posts.presentation.uimodel.PostUiModel
import com.islam.tasks.posts.presentation.view.PostsFragment
import com.islam.tasks.users.presentation.uimodel.UserUiModel
import com.islam.tasks.users.presentation.view.UsersListNav
import javax.inject.Inject

class UsersListNavigation @Inject constructor() : UsersListNav {
    override fun navigateToPosts(activity: FragmentActivity?, user: UserUiModel) {
        activity?.run {
            val userParam = with(user) {
                PostParam(
                    url = url,
                    thumbnailUrl = thumbnailUrl,
                    posts = posts.map { PostUiModel(it.id, it.title, it.body) })
            }
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(
                    R.id.fragment_container_view,
                    PostsFragment.newInstance(userParam)
                )
                addToBackStack(null)
            }
        }
    }
}