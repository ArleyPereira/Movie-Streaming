package br.com.hellodev.moviestreaming.domain.remote.model.favorite

data class MovieFavorite(
    val id: Int? = null,
    val movieId: Int? = null,
    val title: String? = null,
    val backdropPath: String? = null,
    val runtime: Int? = null
)