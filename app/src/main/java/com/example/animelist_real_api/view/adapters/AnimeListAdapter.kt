package com.example.animelist_real_api.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animelist_real_api.databinding.AnimeListItemBinding
import com.example.animelist_real_api.model.api.ApiService
import com.example.animelist_real_api.model.apiModels.Animes
import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.view.IntroAnimeListFragmentDirections

class AnimeListAdapter(
    private val navigate: (id: String)
    -> Unit) :
    RecyclerView
    .Adapter<AnimeListAdapter
    .ViewHolder>() {
    private lateinit var animeData: List<Data>

    class ViewHolder(private val binding:AnimeListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun applyAnimeData(item: Data) = with(binding){
            imageView.loadImage(item.attributes.posterImage.small)
            listItemName.text = item.attributes.slug.uppercase()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AnimeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = animeData[position]
        holder.itemView.setOnClickListener {
            navigate(item.id!!)
        }
        holder.applyAnimeData(item)

    }

    override fun getItemCount(): Int {
        return animeData.size
    }

    fun applyAnimeData(data: List<Data>){
        animeData = data
    }

}
private fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}
