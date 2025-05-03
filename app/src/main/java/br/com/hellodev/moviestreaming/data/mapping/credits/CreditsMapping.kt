package br.com.hellodev.moviestreaming.data.mapping.credits

import br.com.hellodev.moviestreaming.data.mapping.cast.toDomain
import br.com.hellodev.moviestreaming.data.remote.model.credits.CreditsResponse
import br.com.hellodev.moviestreaming.domain.remote.model.credits.Credits

fun CreditsResponse.toDomain(): Credits {
    return Credits(
        cast = cast?.map { it.toDomain() }
    )
}