package com.example.animelist_real_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animelist_real_api.model.MainRepo
import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimeDetailsViewModel: ViewModel() {

     val repo by lazy { MainRepo }

    private val _viewState: MutableLiveData<Resource<List<Data>>> = MutableLiveData(Resource.Loading())
    val viewState: LiveData<Resource<List<Data>>> get() = _viewState

    fun getAnimeDetailsById(id:String){
        viewModelScope.launch(Dispatchers.Main) {
            _viewState.value = repo.getAnimeById(id)
        }
    }
}