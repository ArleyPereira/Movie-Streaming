package br.com.hellodev.moviestreaming.presenter.features.genre.state

import br.com.hellodev.moviestreaming.domain.remote.model.genre.GenreUser

data class GenreState(
    val selectedGenreUser: GenreUser? = null,
    val genreUsers: List<GenreUser> = emptyList(),
)
