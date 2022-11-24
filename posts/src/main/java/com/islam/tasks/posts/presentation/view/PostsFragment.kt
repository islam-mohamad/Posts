package com.islam.tasks.posts.presentation.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.islam.tasks.posts.databinding.FragmentPostsBinding
import com.islam.tasks.posts.presentation.uimodel.PostParam
import com.squareup.picasso.Picasso

class PostsFragment : Fragment() {
    private val postsParam: PostParam? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(USER_ID_KEY, PostParam::class.java)
        } else {
            arguments?.getParcelable(USER_ID_KEY) as PostParam?
        }
    }

    private val adapter by lazy { PostsAdapter() }

    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() = with(binding) {
        postsRv.adapter = adapter
        adapter.submitList(postsParam?.posts)
        Picasso.get().load(postsParam?.url).into(userIv)
    }

    companion object {
        private const val USER_ID_KEY = "USER_ID_KEY"
        fun newInstance(postParam: PostParam): PostsFragment {
            val bundle = Bundle().apply { putParcelable(USER_ID_KEY, postParam) }
            return PostsFragment().apply { arguments = bundle }
        }
    }
}