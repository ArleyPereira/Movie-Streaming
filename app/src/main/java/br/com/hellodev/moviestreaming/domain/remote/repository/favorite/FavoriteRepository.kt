package br.com.hellodev.moviestreaming.domain.remote.repository.favorite

import br.com.hellodev.moviestreaming.domain.remote.model.favorite.MovieFavorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insert(movieFavorite: MovieFavorite)

    suspend fun getFavoriteById(id: Int): MovieFavorite?

    fun getAll(): Flow<List<MovieFavorite>>

    suspend fun delete(movieFavorite: MovieFavorite)

}