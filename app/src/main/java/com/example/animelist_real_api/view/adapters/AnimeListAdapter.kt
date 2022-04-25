package com.example.animelist_real_api.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animelist_real_api.databinding.AnimeListItemBinding
import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.model.apiModels.FavoritesPreview
import kotlin.math.log

class AnimeListAdapter(
    private val navigate: (id: String) -> Unit,
    private val addFavoriteAnime: (anime: FavoritesPreview, id: String, binding: AnimeListItemBinding) -> Unit
) :
    RecyclerView
    .Adapter<AnimeListAdapter
    .ViewHolder>() {

    private var currentFavorites: List<FavoritesPreview> = listOf()
    private lateinit var animeData: List<Data>

    class ViewHolder(private val binding: AnimeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun applyAnimeData(item: Data) = with(binding) {
            imageView.loadImage(item.attributes.posterImage.large)
            listItemName.text = editTitle(item)
        }

        fun isAnimeFavorite(favorites: List<FavoritesPreview>, anime: Data) {
            val favoriteList = mutableListOf<String>()
            favorites.forEach { favorite ->
                favoriteList.add(favorite.animeId)
            }
            favoriteList.forEach { favorite ->
                if (favorite == anime.id) {
                    binding.favoritesBtn.text = "Saved to Favorites!"
                    binding.favoritesBtn.isClickable = false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimeListItemBinding
            .inflate(
                LayoutInflater
                    .from(parent.context), parent, false
            )
        return ViewHolder(binding).apply {
            binding.favoritesBtn.setOnClickListener {
                addFavoriteAnime(
                    FavoritesPreview(
                        animeId = animeData[bindingAdapterPosition].id!!,
                        animeTitle = editTitle(animeData[adapterPosition]),
                        animePosterImg = animeData[adapterPosition].attributes.posterImage.large
                    ), animeData[bindingAdapterPosition].id!!, binding
                )
                currentFavorites.forEach { favorite ->
                    if (favorite.animeId == animeData[adapterPosition].id) {
                        binding.favoritesBtn.text = "ADDED!"
                        binding.favoritesBtn.isClickable = false
                    }
                }
            }
        }
    }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val item = animeData[position]
                holder.itemView.setOnClickListener {
                    navigate(item.id!!)
                }
                holder.isAnimeFavorite(currentFavorites, item)
                holder.applyAnimeData(item)
            }

            override fun getItemCount(): Int {
                return animeData.size
            }

            fun applyAnimeData(data: List<Data>) {
                animeData = data
            }

            fun applyFavorites(favorites: List<FavoritesPreview>) {
                currentFavorites = favorites
            }
        }

        private fun ImageView.loadImage(url: String) {
            Glide.with(context).load(url).into(this)
        }

        private fun editTitle(item: Data): String {
            val title = item.attributes.slug.uppercase()
            var newTitle = ""
            title.map { letter ->
                if (letter != '-') {
                    newTitle += letter
                } else {
                    newTitle += " "
                }
            }
            return newTitle
        }