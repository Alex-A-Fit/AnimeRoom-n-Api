package com.example.animelist_real_api.model.apiModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoverImage(
    val large: String = "",
    val original: String = "",
    val small: String = "",
    val tiny: String = ""
): Parcelable