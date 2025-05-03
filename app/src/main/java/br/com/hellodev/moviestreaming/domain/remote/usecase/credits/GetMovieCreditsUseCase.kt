package br.com.hellodev.moviestreaming.domain.remote.usecase.credits

import br.com.hellodev.moviestreaming.domain.remote.repository.movie.MovieRepository

class GetMovieCreditsUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int) = repository.credits(movieId)
}