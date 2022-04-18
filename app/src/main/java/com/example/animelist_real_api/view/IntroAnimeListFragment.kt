package com.example.animelist_real_api.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animelist_real_api.R
import com.example.animelist_real_api.databinding.FragmentIntroAnimeListBinding
import com.example.animelist_real_api.util.Resource
import com.example.animelist_real_api.view.adapters.AnimeListAdapter
import com.example.animelist_real_api.viewmodel.AnimeListViewModel

class IntroAnimeListFragment : Fragment() {

    private var _binding: FragmentIntroAnimeListBinding? = null
    private val binding: FragmentIntroAnimeListBinding get() = _binding!!

    private val viewmodel by viewModels<AnimeListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentIntroAnimeListBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        viewmodel.viewState.observe(viewLifecycleOwner){ viewState ->
            Log.d("new API", "API INFO: $viewState")
            when(viewState) {
                is Resource.Error -> {}
                Resource.Loading -> {
                    pbCircularLoading.visibility = View.VISIBLE
                    tvLoadingText.isVisible = true
                }
                is Resource.Success -> {
                    pbCircularLoading.visibility = View.INVISIBLE
                    tvLoadingText.isVisible = false
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    recyclerView.adapter = AnimeListAdapter(::navigate).apply {
                        applyAnimeData(viewState.data)
                    }
                }
            }
        }
    }
    private fun navigate(id: String) {
        val action = IntroAnimeListFragmentDirections.actionIntroAnimeListFragmentToAnimeDetailsFragment(id)
        findNavController().navigate(action)
    }
}