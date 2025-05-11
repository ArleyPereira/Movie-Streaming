package br.com.hellodev.moviestreaming.data.mapping.reviews

import br.com.hellodev.moviestreaming.data.remote.model.author.AuthorDetailsResponse
import br.com.hellodev.moviestreaming.data.remote.model.author.ReviewResponse
import br.com.hellodev.moviestreaming.domain.remote.model.reviews.AuthorDetails
import br.com.hellodev.moviestreaming.domain.remote.model.reviews.Review

fun AuthorDetailsResponse.toDomain(): AuthorDetails {
    return AuthorDetails(
        name = name,
        avatarPath = "https://image.tmdb.org/t/p/original/$avatarPath",
        rating = rating
    )
}

fun ReviewResponse.toDomain(): Review {
    return Review(
        authorDetails = authorDetails?.toDomain(),
        content = content,
        createdAt = createdAt
    )
}