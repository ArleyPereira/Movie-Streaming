package br.com.hellodev.moviestreaming.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.hellodev.moviestreaming.core.constans.Database.FAVORITE_TABLE_NAME

@Entity(tableName = FAVORITE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "runtime")
    val runtime: Int
)