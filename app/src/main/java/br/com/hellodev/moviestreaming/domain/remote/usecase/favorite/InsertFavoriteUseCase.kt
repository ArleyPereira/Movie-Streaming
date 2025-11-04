package br.com.hellodev.moviestreaming.domain.remote.usecase.favorite

import br.com.hellodev.moviestreaming.domain.remote.model.favorite.MovieFavorite
import br.com.hellodev.moviestreaming.domain.remote.repository.favorite.FavoriteRepository

class InsertFavoriteUseCase(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(movieFavorite: MovieFavorite) {
        repository.insert(movieFavorite)
    }
}