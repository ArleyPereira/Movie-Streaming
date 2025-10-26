package br.com.hellodev.moviestreaming.domain.local.usecase.favorite

import br.com.hellodev.moviestreaming.data.local.entity.FavoriteEntity
import br.com.hellodev.moviestreaming.domain.local.repository.favorite.FavoriteRepository

class InsertFavoriteUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(movie: FavoriteEntity) = repository.insert(movie)

}