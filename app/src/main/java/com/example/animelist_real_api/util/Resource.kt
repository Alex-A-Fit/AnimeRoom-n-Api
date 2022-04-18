package com.example.animelist_real_api.util

import com.example.animelist_real_api.model.apiModels.Animes
import com.example.animelist_real_api.model.apiModels.Data

sealed class Resource(data: List<Data>?, errorMsg: String?){
    data class Success(val data: List<Data>): Resource(data, null)
    object Loading: Resource(null, null)
    data class Error(val errorMsg: String ): Resource(null, errorMsg)
}
