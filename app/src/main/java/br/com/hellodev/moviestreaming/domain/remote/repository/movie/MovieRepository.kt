package br.com.hellodev.moviestreaming.domain.remote.repository.movie

import br.com.hellodev.moviestreaming.domain.remote.model.base.BaseResponse
import br.com.hellodev.moviestreaming.domain.remote.model.cast.Cast
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

interface MovieRepository {

    suspend fun nowPlaying(): BaseResponse<List<Movie>>

    suspend fun popular(): BaseResponse<List<Movie>>

    suspend fun topRated(): BaseResponse<List<Movie>>

    suspend fun upcoming(): BaseResponse<List<Movie>>

    suspend fun details(movieId: Int): BaseResponse<Movie>

    suspend fun credits(movieId: Int): BaseResponse<List<Cast>>

}