package br.com.hellodev.moviestreaming.domain.remote.repository.authentication

interface AuthenticationRepository {

    suspend fun register(email: String, password: String)

    suspend fun login(email: String, password: String)

}