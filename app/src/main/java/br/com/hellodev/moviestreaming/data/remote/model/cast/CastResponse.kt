package br.com.hellodev.moviestreaming.data.remote.model.cast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastResponse(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("profile_path")
    val profilePath: String? = null
)
