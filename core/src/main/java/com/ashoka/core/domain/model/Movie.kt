package com.ashoka.core.domain.model

import android.os.Parcelable
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