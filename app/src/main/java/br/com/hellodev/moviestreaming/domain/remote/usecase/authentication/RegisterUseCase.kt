package br.com.hellodev.moviestreaming.domain.remote.usecase.authentication

import br.com.hellodev.moviestreaming.domain.remote.repository.authentication.SignupRepository

class RegisterUseCase(
    private val repository: SignupRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        repository.register(email, password)
    }

}