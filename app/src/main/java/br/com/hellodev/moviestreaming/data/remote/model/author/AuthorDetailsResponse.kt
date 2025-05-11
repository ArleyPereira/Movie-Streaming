package br.com.hellodev.moviestreaming.data.remote.model.author

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetailsResponse(
    @SerialName("name")
    val name: String? = null,

    @SerialName("avatar_path")
    val avatarPath: String? = null,

    @SerialName("rating")
    val rating: Int? = null
)
