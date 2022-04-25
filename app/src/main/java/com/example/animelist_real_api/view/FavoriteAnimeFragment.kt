package com.example.animelist_real_api.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.animelist_real_api.databinding.FragmentFavoriteAnimeBinding

class FavoriteAnimeFragment : Fragment() {
    private var _binding: FragmentFavoriteAnimeBinding? = null
    private val binding: FragmentFavoriteAnimeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFavoriteAnimeBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitViews()
    }

    private fun onInitViews() {
    }
}