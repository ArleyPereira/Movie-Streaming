package br.com.hellodev.moviestreaming.data.remote.model.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null
)