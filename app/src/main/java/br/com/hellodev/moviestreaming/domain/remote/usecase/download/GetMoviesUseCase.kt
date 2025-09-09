package br.com.hellodev.moviestreaming.domain.remote.usecase.download

import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.domain.remote.repository.download.DownloadRepository

class GetMoviesUseCase(
    private val repository: DownloadRepository
) {

    suspend operator fun invoke(): List<Movie> {
        return repository.list()
    }

}