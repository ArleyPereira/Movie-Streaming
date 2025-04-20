package br.com.hellodev.moviestreaming.domain.remote.usecase.movie

import br.com.hellodev.moviestreaming.domain.remote.model.base.BaseResponse
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.domain.remote.repository.movie.MovieRepository

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movieId: Int): BaseResponse<Movie> {
        val response = repository.details(movieId)
        return BaseResponse(
            results = response.results,
            statusCode = response.statusCode,
            message = response.message,
            resultStatus = response.resultStatus
        )
    }

}