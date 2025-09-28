package br.com.hellodev.moviestreaming.domain.remote.repository.download

import br.com.hellodev.moviestreaming.domain.remote.model.movie.MovieDownload

interface DownloadRepository {

    suspend fun save(movie: MovieDownload)

    suspend fun delete(movie: MovieDownload?)

    suspend fun list(): List<MovieDownload>

}