package com.example.animelist_real_api.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animelist_real_api.model.AnimeRepoImpl
import com.example.animelist_real_api.viewmodel.AnimeListViewModel

class ViewModelFactoryAnimeList(
    private val repo: AnimeRepoImpl
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimeListViewModel(repo) as T
    }
}