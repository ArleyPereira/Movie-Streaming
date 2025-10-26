package br.com.hellodev.moviestreaming.domain.local.usecase.favorite

import br.com.hellodev.moviestreaming.domain.local.repository.favorite.FavoriteRepository

class GetFavoritesUseCase(
    private val repository: FavoriteRepository
) {

    operator fun invoke() = repository.getAll()

}