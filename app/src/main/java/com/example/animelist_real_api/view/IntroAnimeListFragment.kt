package com.example.animelist_real_api.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animelist_real_api.databinding.AnimeListItemBinding
import com.example.animelist_real_api.databinding.FragmentIntroAnimeListBinding
import com.example.animelist_real_api.model.AnimeRepoImpl
import com.example.animelist_real_api.model.apiModels.FavoritesPreview
import com.example.animelist_real_api.util.Resource
import com.example.animelist_real_api.view.adapters.AnimeListAdapter
import com.example.animelist_real_api.viewmodel.AnimeListViewModel
import com.example.animelist_real_api.viewmodel.factory.ViewModelFactoryAnimeList

class IntroAnimeListFragment : Fragment() {

    private var _binding: FragmentIntroAnimeListBinding? = null
    private val binding: FragmentIntroAnimeListBinding get() = _binding!!

    private var favorites: List<FavoritesPreview> = listOf(FavoritesPreview())

    val repoImp by lazy {
        AnimeRepoImpl(requireContext())
    }

    private val viewmodel by viewModels<AnimeListViewModel>() {
        ViewModelFactoryAnimeList(repoImp)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentIntroAnimeListBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBar.visibility = View.GONE
        initFavorites()
        initListener()
    }
    private fun initFavorites(){
        viewmodel.getFavoriteAnime()
        viewmodel.favoriteAnime.observe(viewLifecycleOwner){favorite ->
            when(favorite){
                is Resource.Error -> {}
                Resource.Loading<Unit>() -> {}
                is Resource.Success -> {
                    favorites = favorite.data
                }
                else -> {}
            }

        }
    }

    private fun initViews() = with(binding) {
        animeRV.layoutManager = LinearLayoutManager(context)
        "Grabbing that good good Anime".also { tvLoadingText.text = it }

        viewmodel.viewState.observe(viewLifecycleOwner){ viewState ->
            when(viewState) {
                is Resource.Error -> {}
                Resource.Loading<Unit>() -> {
                    pbCircularLoading.visibility = View.VISIBLE
                    tvLoadingText.isVisible = true
                    topNavBar.isVisible = false
                    animeRV.isVisible = false
                }
                is Resource.Success -> {
                    topNavBar.isVisible = true
                    animeRV.isVisible = true
                    binding.searchBar.visibility = View.VISIBLE
                    pbCircularLoading.visibility = View.INVISIBLE
                    tvLoadingText.isVisible = false
                    animeRV.adapter = AnimeListAdapter(::navigate, ::addFavoriteAnime).apply {
                        applyAnimeData(viewState.data)
                        applyFavorites(favorites)
                    }
                }
                else -> {}
            }
        }
    }

    private fun initListener() = with(binding){
        pbCircularLoading.visibility = View.INVISIBLE
        "Choose a list of Anime".also { tvLoadingText.text = it }
        tvLoadingText.isVisible = true
        getAnimeBtn.setOnClickListener {
            pbCircularLoading.visibility = View.VISIBLE
            viewmodel.getAnime()
            initViews()
        }
        getTrendingAnimeBtn.setOnClickListener {
            pbCircularLoading.visibility = View.VISIBLE
            viewmodel.getTrendingAnime()
            initViews()
        }
    }
    private fun navigate(id: String) {
        val action = IntroAnimeListFragmentDirections.actionIntroAnimeListFragmentToAnimeDetailsFragment(id)
        findNavController().navigate(action)
    }
    private fun addFavoriteAnime(anime: FavoritesPreview, id: String, binding: AnimeListItemBinding){
        viewmodel.addFavoriteAnime(anime)
    }
}