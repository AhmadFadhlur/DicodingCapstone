package com.ashoka.core.domain.model

import android.os.Parcelable
import com.ashoka.core.data.remote.response.GenresItem
import com.ashoka.core.data.remote.response.ProductionCompaniesItem
import com.ashoka.core.data.remote.response.ProductionCountriesItem
import com.ashoka.core.data.remote.response.SpokenLanguagesItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DetailMovie(
    val id:Int,
    val backdropPath: String,
    val originalTitle: String,
    val title: String,
    val genres: List<GenreMovie>,
    val popularity: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val homepage: String,
) : Parcelable