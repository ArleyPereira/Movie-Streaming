package br.com.hellodev.moviestreaming.presenter.features.main.search.state

import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

data class SearchState(
    val isLoading: Boolean = true,
    val query: String = "",
    val movies: List<Movie> = emptyList(),
)
