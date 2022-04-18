package com.example.animelist_real_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist_real_api.model.MainRepo
import com.example.animelist_real_api.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeListViewModel: ViewModel() {

     val repo by lazy { MainRepo }

    private val _viewState: MutableLiveData<Resource> = MutableLiveData(Resource.Loading)
    val viewState: LiveData<Resource> get() = _viewState

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _viewState.value = repo.getAnime()
        }
    }
}