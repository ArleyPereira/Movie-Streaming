package br.com.hellodev.moviestreaming.domain.remote.repository.movie

import br.com.hellodev.moviestreaming.domain.remote.model.base.BaseResponse
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

interface MovieRepository {

    suspend fun nowPlaying(): BaseResponse<List<Movie>>

}