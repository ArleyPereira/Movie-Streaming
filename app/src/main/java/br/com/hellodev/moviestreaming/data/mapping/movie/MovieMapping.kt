package br.com.hellodev.moviestreaming.data.mapping.movie

import br.com.hellodev.moviestreaming.data.remote.model.movie.GenreResponse
import br.com.hellodev.moviestreaming.data.remote.model.movie.MovieResponse
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Genre
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

fun MovieResponse.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        budget = budget,
        genres = genreResponses?.map { it?.toDomain() },
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}