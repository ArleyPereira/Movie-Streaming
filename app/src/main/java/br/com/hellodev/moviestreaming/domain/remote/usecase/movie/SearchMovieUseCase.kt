package br.com.hellodev.moviestreaming.domain.remote.usecase.movie

import br.com.hellodev.moviestreaming.domain.remote.repository.movie.MovieRepository

class SearchMovieUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(query: String) = repository.search(query)

}