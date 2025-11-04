package br.com.hellodev.moviestreaming.domain.remote.usecase.favorite

import br.com.hellodev.moviestreaming.domain.remote.model.favorite.MovieFavorite
import br.com.hellodev.moviestreaming.domain.remote.repository.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<MovieFavorite>> {
        return repository.getAll()
    }
}