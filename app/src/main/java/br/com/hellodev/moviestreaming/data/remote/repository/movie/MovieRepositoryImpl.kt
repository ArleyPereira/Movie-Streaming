package br.com.hellodev.moviestreaming.data.remote.repository.movie

import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import br.com.hellodev.moviestreaming.data.api.ApiRequest
import br.com.hellodev.moviestreaming.data.mapping.credits.toDomain
import br.com.hellodev.moviestreaming.data.mapping.movie.toDomain
import br.com.hellodev.moviestreaming.data.mapping.reviews.toDomain
import br.com.hellodev.moviestreaming.data.remote.model.author.ReviewResponse
import br.com.hellodev.moviestreaming.data.remote.model.credits.CreditsResponse
import br.com.hellodev.moviestreaming.data.remote.model.movie.MovieResponse
import br.com.hellodev.moviestreaming.data.routes.NOW_PLAYING_ROUTE
import br.com.hellodev.moviestreaming.data.routes.POPULAR_ROUTE
import br.com.hellodev.moviestreaming.data.routes.TOP_RATED_ROUTE
import br.com.hellodev.moviestreaming.data.routes.UPCOMING_ROUTE
import br.com.hellodev.moviestreaming.data.routes.movieCreditsRoute
import br.com.hellodev.moviestreaming.data.routes.movieDetailsRoute
import br.com.hellodev.moviestreaming.data.routes.movieReviewsRoute
import br.com.hellodev.moviestreaming.domain.remote.model.base.BaseResponse
import br.com.hellodev.moviestreaming.domain.remote.model.credits.Credits
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.domain.remote.model.reviews.Review
import br.com.hellodev.moviestreaming.domain.remote.repository.movie.MovieRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class MovieRepositoryImpl(
    private val httpClient: HttpClient,
    private val apiRequest: ApiRequest
) : MovieRepository {

    override suspend fun nowPlaying(): BaseResponse<List<Movie>> {
        return try {
            val response = httpClient.get(NOW_PLAYING_ROUTE)

            apiRequest<List<MovieResponse>, List<Movie>>(response) { movies ->
                movies.map { it.toDomain() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

    override suspend fun popular(): BaseResponse<List<Movie>> {
        return try {
            val response = httpClient.get(POPULAR_ROUTE)

            apiRequest<List<MovieResponse>, List<Movie>>(response) { movies ->
                movies.map { it.toDomain() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

    override suspend fun topRated(): BaseResponse<List<Movie>> {
        return try {
            val response = httpClient.get(TOP_RATED_ROUTE)

            apiRequest<List<MovieResponse>, List<Movie>>(response) { movies ->
                movies.map { it.toDomain() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

    override suspend fun upcoming(): BaseResponse<List<Movie>> {
        return try {
            val response = httpClient.get(UPCOMING_ROUTE)

            apiRequest<List<MovieResponse>, List<Movie>>(response) { movies ->
                movies.map { it.toDomain() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

    override suspend fun details(movieId: Int): BaseResponse<Movie> {
        return try {
            val response = httpClient.get(movieDetailsRoute(movieId)) {
                url {
                    parameters.append("language", "pt-br")
                }
            }
            apiRequest<MovieResponse, Movie>(
                response = response,
                isPaginated = false
            ) { it.toDomain() }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

    override suspend fun credits(movieId: Int): BaseResponse<Credits> {
        return try {
            val response = httpClient.get(movieCreditsRoute(movieId)) {
                url {
                    parameters.append("language", "pt-br")
                }
            }
            apiRequest<CreditsResponse, Credits>(
                response = response,
                isPaginated = false
            ) { it.toDomain() }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

    override suspend fun reviews(movieId: Int): BaseResponse<List<Review>> {
        return try {
            val response = httpClient.get(movieReviewsRoute(movieId)) {
                url {
                    parameters.append("language", "pt-br")
                }
            }
            apiRequest<List<ReviewResponse>, List<Review>>(response) { reviews ->
                reviews.map { it.toDomain() }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            BaseResponse(
                results = null,
                statusCode = null,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

}