package com.example.animelist_real_api.model.apiModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animes(
    val `data`: List<Data>
): Parcelable