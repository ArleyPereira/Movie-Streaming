package br.com.hellodev.moviestreaming.presenter.features.genre.state

import br.com.hellodev.moviestreaming.domain.remote.model.genre.Genre

data class GenreState(
    val selectedGenre: Genre? = null,
    val genres: List<Genre> = emptyList(),
)
