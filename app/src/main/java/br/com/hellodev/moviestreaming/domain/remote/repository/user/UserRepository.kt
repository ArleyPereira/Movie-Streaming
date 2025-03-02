package br.com.hellodev.moviestreaming.domain.remote.repository.user

import android.net.Uri
import br.com.hellodev.moviestreaming.domain.remote.model.user.User

interface UserRepository {

    suspend fun save(user: User)

    suspend fun getUser(): User

    suspend fun saveImage(uri: Uri): String

}