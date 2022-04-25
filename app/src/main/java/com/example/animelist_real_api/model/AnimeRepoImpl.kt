package com.example.animelist_real_api.model

import android.content.Context
import com.example.animelist_real_api.model.api.ApiService
import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.model.apiModels.FavoritesPreview
import com.example.animelist_real_api.model.room.AnimeFavoritesDatabase
import com.example.animelist_real_api.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class AnimeRepoImpl(context: Context): AnimeRepo {

    private val apiService by lazy { ApiService.retrofit }
    private val animeDao = AnimeFavoritesDatabase.createDatabaseInstance(context).animeDao()


    override suspend fun getAnimeFromApi(): Resource<List<Data>>? = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = apiService.fetchAnimes()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.animeList)
            } else {
                Resource.Error("Something went Wrong! D:")
            }
        } catch (e: Exception) {
            e.localizedMessage?.let { Resource.Error(it) }
        }
    }

    override suspend fun getTrendingAnimeFromApi(): Resource<List<Data>>? = withContext(Dispatchers.IO){
        return@withContext try{
            val response = apiService.fetchTrendingAnimes()
            if(response.isSuccessful){
                Resource.Success(response.body()!!.animeList)
            }else{
                Resource.Error("Something went Wrong! D:")
            }
        } catch (e: Exception){
            e.localizedMessage?.let { Resource.Error(it) }
        }
    }

    override suspend fun getAnimeByIdFromApi(animeId: String): Resource<List<Data>>? = withContext(Dispatchers.IO){
        return@withContext try{
            val response = apiService.fetchAnimeById(animeId)
            if(response.isSuccessful){
                Resource.Success(response.body()!!.animeList)
            }else{
                Resource.Error("Something went Wrong! D:")
            }
        } catch (e: Exception){
            e.localizedMessage?.let { Resource.Error(it) }
        }
    }

    override suspend fun addFavoriteAnimeToDb(anime: FavoritesPreview) = withContext(Dispatchers.IO) {
        animeDao.addFavoriteAnime(anime)
    }

    override suspend fun deleteFavoriteAnimeToDb(anime: FavoritesPreview) = withContext(Dispatchers.IO){
        animeDao.deleteFavoriteAnime(anime)
    }

    override suspend fun getFavoriteAnimeFromDb(): Resource<List<FavoritesPreview>> = withContext(Dispatchers.IO){
            return@withContext try {
                val response = animeDao.getFavoriteAnime()
                Resource.Success(response)
            } catch (e: Exception){
                Resource.Error(e.localizedMessage ?: "Favorites Not Found")
            }
        }
}