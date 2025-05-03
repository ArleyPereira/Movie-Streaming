package br.com.hellodev.moviestreaming.presenter.features.main.details.state

import br.com.hellodev.moviestreaming.domain.remote.model.credits.Credits
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val credits: Credits? = null
)
