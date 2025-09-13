package br.com.hellodev.moviestreaming.presenter.features.main.download.state

import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

data class DownloadState(
    val isLoading: Boolean = true,
    val movies: List<Movie> = emptyList()
)
