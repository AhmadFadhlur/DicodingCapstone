package com.ashoka.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id:Int,
    val posterPath: String,
    val title: String,
    val originalTitle: String,
    val popularity: String,
    val releaseDate: String,
) : Parcelable