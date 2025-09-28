package br.com.hellodev.moviestreaming.domain.remote.model.movie

data class MovieDownload(
    val backdropPath: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val runtime: Int? = null
)