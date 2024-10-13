package br.com.hellodev.moviestreaming.domain.remote.repository.user

import br.com.hellodev.moviestreaming.domain.remote.model.User

interface UserRepository {

    suspend fun save(user: User)

    suspend fun getUser(): User

}