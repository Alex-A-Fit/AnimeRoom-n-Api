package com.example.animelist_real_api.model

import com.example.animelist_real_api.model.api.ApiService
import com.example.animelist_real_api.model.apiModels.Animes
import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

object MainRepo {
    private val apiService by lazy { ApiService.retrofit }

    suspend fun getAnime() = withContext(Dispatchers.IO){
        return@withContext try{
            val response = apiService.fetchAnimes()
            if(response.isSuccessful){
                Resource.Success(response.body()!!.animeList)
            }else{
                Resource.Error("Something went Wrong! D:")
            }
        } catch (e: Exception){
            e.localizedMessage?.let { Resource.Error(it) }
        }
    }

    suspend fun getAnimeById(id : String) = withContext(Dispatchers.IO){
        return@withContext try{
            val response = apiService.fetchAnimeById(id)
            if(response.isSuccessful){
                Resource.Success(response.body()!!.animeList)
            }else{
                Resource.Error("Something went Wrong! D:")
            }
        } catch (e: Exception){
            e.localizedMessage?.let { Resource.Error(it) }
        }
    }
}