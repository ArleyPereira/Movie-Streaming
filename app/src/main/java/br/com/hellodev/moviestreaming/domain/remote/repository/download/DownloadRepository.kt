package br.com.hellodev.moviestreaming.domain.remote.repository.download

import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

interface DownloadRepository {

    suspend fun save(movie: Movie)

    suspend fun delete(movie: Movie?)

    suspend fun list(): List<Movie>

}