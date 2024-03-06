package com.ashoka.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashoka.core.data.local.entities.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun MovieDAO() : FavoriteMovieDAO

}