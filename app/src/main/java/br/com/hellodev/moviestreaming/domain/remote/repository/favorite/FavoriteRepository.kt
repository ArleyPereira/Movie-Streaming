package br.com.hellodev.moviestreaming.domain.remote.repository.favorite

import br.com.hellodev.moviestreaming.domain.remote.model.favorite.MovieFavorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insert(movieFavorite: MovieFavorite)

    fun getAll(): Flow<List<MovieFavorite>>

    suspend fun delete(movieFavorite: MovieFavorite)

}