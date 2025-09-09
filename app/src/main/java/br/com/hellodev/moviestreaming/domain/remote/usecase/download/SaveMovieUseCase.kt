package br.com.hellodev.moviestreaming.domain.remote.usecase.download

import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.domain.remote.repository.download.DownloadRepository

class SaveMovieUseCase(
    private val repository: DownloadRepository
) {

    suspend operator fun invoke(movie: Movie) {
        repository.save(movie)
    }

}