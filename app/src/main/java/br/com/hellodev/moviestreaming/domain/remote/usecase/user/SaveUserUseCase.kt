package br.com.hellodev.moviestreaming.domain.remote.usecase.user

import br.com.hellodev.moviestreaming.domain.remote.model.User
import br.com.hellodev.moviestreaming.domain.remote.repository.user.UserRepository

class SaveUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        repository.save(user)
    }

}