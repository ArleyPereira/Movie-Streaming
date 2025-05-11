package br.com.hellodev.moviestreaming.domain.remote.usecase.reviews

import br.com.hellodev.moviestreaming.domain.remote.repository.movie.MovieRepository

class GetMovieReviewsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) = repository.reviews(movieId)
}