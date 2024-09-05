package br.com.hellodev.moviestreaming.domain.remote.repository.authentication

interface SignupRepository {

    suspend fun register(email: String, password: String)

}