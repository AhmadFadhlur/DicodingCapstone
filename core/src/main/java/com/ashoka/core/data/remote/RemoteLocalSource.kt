package com.ashoka.core.data.remote

import com.ashoka.core.data.local.entities.FavoriteMovieEntity
import com.ashoka.core.data.local.room.FavoriteMovieDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteLocalSource @Inject constructor(
    private val favoriteMovieDAO: FavoriteMovieDAO
) {
    fun getMoviesFavorite(): Flow<List<FavoriteMovieEntity>> = favoriteMovieDAO.getMovies()

    fun getMovieFavoriteById(id: Int): Flow<Boolean> = favoriteMovieDAO.getMoviesById(id)

    suspend fun insertMovie(movie: FavoriteMovieEntity) = favoriteMovieDAO.insertMovieFavorite(movie)

    suspend fun deleteMovie(movie: FavoriteMovieEntity) = favoriteMovieDAO.deleteMovieFavorite(movie)
}