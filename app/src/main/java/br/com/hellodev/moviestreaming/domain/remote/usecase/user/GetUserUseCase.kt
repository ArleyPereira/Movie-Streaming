package br.com.hellodev.moviestreaming.domain.remote.usecase.user

import br.com.hellodev.moviestreaming.domain.remote.model.user.User
import br.com.hellodev.moviestreaming.domain.remote.repository.user.UserRepository

class GetUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): User {
        return repository.getUser()
    }

}