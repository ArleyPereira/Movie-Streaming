package br.com.hellodev.moviestreaming.domain.remote.usecase.authentication

import br.com.hellodev.moviestreaming.domain.remote.repository.authentication.AuthenticationRepository

class RegisterUseCase(
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        repository.register(email, password)
    }

}