package br.com.hellodev.moviestreaming.domain.remote.usecase.authentication

import br.com.hellodev.moviestreaming.domain.remote.repository.authentication.AuthenticationRepository

class LoginUseCase(
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        repository.login(email, password)
    }

}