package br.com.hellodev.moviestreaming.data.local.repository.favorite

import br.com.hellodev.moviestreaming.data.local.dao.FavoriteDao
import br.com.hellodev.moviestreaming.data.mapping.favorite.toDomain
import br.com.hellodev.moviestreaming.data.mapping.favorite.toEntity
import br.com.hellodev.moviestreaming.domain.remote.model.favorite.MovieFavorite
import br.com.hellodev.moviestreaming.domain.remote.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val dao: FavoriteDao
) : FavoriteRepository {

    override suspend fun insert(movieFavorite: MovieFavorite) {
        dao.insert(movieFavorite.toEntity())
    }

    override fun getAll(): Flow<List<MovieFavorite>> {
        return dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun delete(movieFavorite: MovieFavorite) {
        dao.delete(movieFavorite.toEntity())
    }

}