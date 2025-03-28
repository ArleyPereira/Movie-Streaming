package br.com.hellodev.moviestreaming.presenter.features.main.home.state

import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val nowPlayingList: List<Movie> = emptyList(),
    val popularList: List<Movie> = emptyList(),
    val topRatedList: List<Movie> = emptyList(),
    val upcomingList: List<Movie> = emptyList()
)
