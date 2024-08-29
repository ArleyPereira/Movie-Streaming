package br.com.hellodev.moviestreaming.domain.remote

interface SignupRepository {

    suspend fun register(email: String, password: String)

}