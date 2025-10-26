package br.com.hellodev.moviestreaming.domain.local.repository.favorite

import br.com.hellodev.moviestreaming.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insert(movie: FavoriteEntity)

    fun getAll(): Flow<List<FavoriteEntity>>

    suspend fun update(movie: FavoriteEntity)

    suspend fun delete(movie: FavoriteEntity)

}