package com.ashoka.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteMovie")
data class FavoriteMovieEntity(
    @PrimaryKey
    val id:Int,
    val posterPath: String,
    val title: String,
    val originalTitle: String,
    val popularity: String,
    val releaseDate: String,

    )