package br.com.hellodev.moviestreaming.domain.remote.model.reviews

data class Review(
    val authorDetails: AuthorDetails? = null,
    val content: String? = null,
    val createdAt: String? = null
)
