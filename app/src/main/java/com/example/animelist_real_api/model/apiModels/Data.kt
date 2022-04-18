package com.example.animelist_real_api.model.apiModels

import android.os.Parcelable
import com.example.animelist_real_api.model.apiModels.Attributes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val attributes: Attributes,
    val id: String? = "",
    val type: String? = ""
): Parcelable