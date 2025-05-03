package br.com.hellodev.moviestreaming.domain.remote.model.credits

import br.com.hellodev.moviestreaming.domain.remote.model.cast.Cast

data class Credits(
    val cast: List<Cast>? = null
)
