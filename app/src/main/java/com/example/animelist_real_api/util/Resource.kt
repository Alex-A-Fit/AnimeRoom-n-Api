package com.example.animelist_real_api.util

import com.example.animelist_real_api.model.apiModels.Data

sealed class Resource<T>(data: T? = null, message: String?) {
    data class Success<T> (val data: T): Resource<T>(data, null)
    class Loading<T> (): Resource<T>(null, null)
    data class Error<T> (val message: String): Resource<T>(null, message)
}
