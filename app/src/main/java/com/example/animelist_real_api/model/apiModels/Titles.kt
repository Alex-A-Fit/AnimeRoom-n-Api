package com.example.animelist_real_api.model.apiModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Titles(
    val en: String = "",
    val en_jp: String = "",
    val en_us: String = "",
    val ja_jp: String = ""
): Parcelable