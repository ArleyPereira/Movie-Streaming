package br.com.hellodev.moviestreaming.data.mapping.favorite

import br.com.hellodev.moviestreaming.data.local.entity.FavoriteEntity
import br.com.hellodev.moviestreaming.domain.remote.model.favorite.MovieFavorite

fun MovieFavorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id ?: 0,
        movieId = this.movieId ?: 0,
        title = this.title ?: "",
        backdropPath = this.backdropPath ?: "",
        runtime = this.runtime ?: 0
    )
}

fun FavoriteEntity.toDomain(): MovieFavorite {
    return MovieFavorite(
        id = this.id,
        movieId = this.movieId,
        title = this.title,
        backdropPath = this.backdropPath,
        runtime = this.runtime
    )
}