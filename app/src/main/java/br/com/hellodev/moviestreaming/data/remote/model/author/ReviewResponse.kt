package br.com.hellodev.moviestreaming.data.remote.model.author

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("author_details")
    val authorDetails: AuthorDetailsResponse? = null,

    @SerialName("content")
    val content: String? = null,

    @SerialName("created_at")
    val createdAt: String? = null
)
