package br.com.hellodev.moviestreaming.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.hellodev.moviestreaming.data.local.dao.favorite.FavoriteDao
import br.com.hellodev.moviestreaming.data.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): FavoriteDao
}