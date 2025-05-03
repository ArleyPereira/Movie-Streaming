package br.com.hellodev.moviestreaming.data.remote.model.credits

import br.com.hellodev.moviestreaming.data.remote.model.cast.CastResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast")
    val cast: List<CastResponse>? = null
)
