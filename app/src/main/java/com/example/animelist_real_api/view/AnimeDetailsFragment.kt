package com.example.animelist_real_api.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.animelist_real_api.R
import com.example.animelist_real_api.databinding.FragmentAnimeDetailsBinding
import com.example.animelist_real_api.model.apiModels.Data
import com.example.animelist_real_api.util.Resource
import com.example.animelist_real_api.viewmodel.AnimeDetailsViewModel
import java.lang.Exception
import kotlin.math.roundToInt

class AnimeDetailsFragment : Fragment() {

    private var _binding: FragmentAnimeDetailsBinding? = null
    private val binding: FragmentAnimeDetailsBinding get() = _binding!!

    private lateinit var animeData: List<Data>

    private val args by navArgs<AnimeDetailsFragmentArgs>()

    private val viewmodel by viewModels<AnimeDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAnimeDetailsBinding.inflate(inflater, container, false).also {
        _binding = it
        viewmodel.getAnimeDetailsById(args.id)
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        binding.AnimeDetailsSV.isVisible = false
        viewmodel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Resource.Error -> {}
                Resource.Loading<Unit>() -> {
                    binding.pbCircularLoading.visibility = View.VISIBLE
                    binding.tvLoadingText.isVisible = true
                }
                is Resource.Success -> {
                    binding.pbCircularLoading.visibility = View.INVISIBLE
                    binding.tvLoadingText.isVisible = false
                    binding.AnimeDetailsSV.isVisible = true
                    animeData = viewState.data
                    val anime = animeData[0]
                    try {
                        CoverArtIV.loadImage(anime.attributes.coverImage.original)

                    } catch(e: Exception){
                        CoverArtIV.loadImage(anime.attributes.posterImage.original)
                    }

                    tvAnimeTitle.text = anime.attributes.slug.uppercase()
                    tvAnimeDesc.text = anime.attributes.description
                    ContentRatingTV.text = anime.attributes.ageRating
                    val rating = anime.attributes.averageRating.toDouble().roundToInt()
                    assessAvgRating(rating)
                }
                else -> {}
            }
        }
    }

    private fun assessAvgRating(rating: Int) = with(binding) {
        when {
            rating >= 90 -> {
                ivStar.setImageResource(R.drawable.filled_star)
                ivStar2.setImageResource(R.drawable.filled_star)
                ivStar3.setImageResource(R.drawable.filled_star)
                ivStar4.setImageResource(R.drawable.filled_star)
                ivStar5.setImageResource(R.drawable.filled_star)
            }
            rating >= 80 -> {
                ivStar.setImageResource(R.drawable.filled_star)
                ivStar2.setImageResource(R.drawable.filled_star)
                ivStar3.setImageResource(R.drawable.filled_star)
                ivStar4.setImageResource(R.drawable.filled_star)
            }
            rating >= 60 -> {
                ivStar.setImageResource(R.drawable.filled_star)
                ivStar2.setImageResource(R.drawable.filled_star)
                ivStar3.setImageResource(R.drawable.filled_star)
            }
            rating >= 40 -> {
                ivStar.setImageResource(R.drawable.filled_star)
                ivStar2.setImageResource(R.drawable.filled_star)
            }
            rating >= 20 -> {
                ivStar.setImageResource(R.drawable.filled_star)
            }
        }
    }
}

private fun ImageView.loadImage(url: String) {
    Glide.with(context).load(url).into(this)
}