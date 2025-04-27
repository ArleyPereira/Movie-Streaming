package br.com.hellodev.moviestreaming.data.mapping.cast

import br.com.hellodev.moviestreaming.data.remote.model.cast.CastResponse
import br.com.hellodev.moviestreaming.domain.remote.model.cast.Cast

fun CastResponse.toDomain(): Cast {
    return Cast(
        id = id,
        name = name,
        profilePath = "https://image.tmdb.org/t/p/original/$profilePath"
    )
}