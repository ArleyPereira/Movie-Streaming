package br.com.hellodev.moviestreaming.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieDownloadEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
}