package com.example.animelist_real_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist_real_api.model.AnimeRepoImpl
import com.example.animelist_real_api.model.MainRepo
import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.model.apiModels.FavoritesPreview
import com.example.animelist_real_api.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeListViewModel(val repo: AnimeRepoImpl): ViewModel() {

    private val _viewState: MutableLiveData<Resource<List<Data>>> = MutableLiveData(Resource.Loading())
    val viewState: LiveData<Resource<List<Data>>> get() = _viewState

    private var _favoriteAnime: MutableLiveData<Resource<List<FavoritesPreview>>> = MutableLiveData(Resource.Loading())
    val favoriteAnime: LiveData<Resource<List<FavoritesPreview>>> get() = _favoriteAnime

    fun getAnime() {
        viewModelScope.launch(Dispatchers.Main) {
            _viewState.value = repo.getAnimeFromApi()
        }
    }
    fun getTrendingAnime() {
        viewModelScope.launch(Dispatchers.Main) {
            _viewState.value = repo.getTrendingAnimeFromApi()
        }
    }

    fun addFavoriteAnime(anime: FavoritesPreview) = viewModelScope.launch(Dispatchers.Main) {
        repo.addFavoriteAnimeToDb(anime)
    }

    fun getFavoriteAnime() = viewModelScope.launch(Dispatchers.Main) {
        val response = repo.getFavoriteAnimeFromDb()
        _favoriteAnime.value = response
    }

}