package com.islam.tasks.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.islam.tasks.posts.databinding.FragmentPostsBinding

class PostsFragment : Fragment() {
    private val userId by lazy {
        arguments?.getInt(USER_ID_KEY)
    }

    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val USER_ID_KEY = "USER_ID_KEY"
        fun newInstance(userId: Int): PostsFragment {
            val bundle = Bundle().apply { putInt(USER_ID_KEY, userId) }
            return PostsFragment().apply { arguments = bundle }
        }
    }
}