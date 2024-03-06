package com.ashoka.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ashoka.core.data.local.entities.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDAO {
    @Query("SELECT * FROM favoriteMovie")
    fun getMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT COUNT(*) FROM favoriteMovie WHERE id = :id")
    fun getMoviesById(id: Int): Flow<Boolean>

    @Insert
    suspend fun insertMovieFavorite(movie: FavoriteMovieEntity)

    @Delete
    suspend fun deleteMovieFavorite(movie: FavoriteMovieEntity)
}