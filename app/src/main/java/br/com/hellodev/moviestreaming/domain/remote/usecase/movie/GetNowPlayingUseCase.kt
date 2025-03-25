package br.com.hellodev.moviestreaming.domain.remote.usecase.movie

import br.com.hellodev.moviestreaming.domain.remote.repository.movie.MovieRepository

class GetNowPlayingUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke() = repository.nowPlaying()

}