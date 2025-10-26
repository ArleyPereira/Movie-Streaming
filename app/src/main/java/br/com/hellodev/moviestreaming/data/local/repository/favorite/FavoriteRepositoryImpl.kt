package br.com.hellodev.moviestreaming.data.local.repository.favorite

import br.com.hellodev.moviestreaming.data.local.dao.favorite.FavoriteDao
import br.com.hellodev.moviestreaming.data.local.entity.FavoriteEntity
import br.com.hellodev.moviestreaming.domain.local.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class FavoriteRepositoryImpl(
    private val dao: FavoriteDao
) : FavoriteRepository {

    override suspend fun insert(movie: FavoriteEntity) {
        dao.insert(movie)
    }

    override fun getAll(): Flow<List<FavoriteEntity>> {
        return dao.getAll()
    }

    override suspend fun update(movie: FavoriteEntity) {
        dao.update(movie)
    }

    override suspend fun delete(movie: FavoriteEntity) {
        dao.delete(movie)
    }

}