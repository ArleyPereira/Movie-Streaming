package br.com.hellodev.moviestreaming.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.hellodev.moviestreaming.core.constans.Database.FAVORITE_TABLE_NAME
import br.com.hellodev.moviestreaming.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM $FAVORITE_TABLE_NAME WHERE movie_id = :id")
    suspend fun getFavoriteById(id: Int): FavoriteEntity?

    @Query("SELECT * FROM $FAVORITE_TABLE_NAME")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)

}