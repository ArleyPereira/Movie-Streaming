package br.com.hellodev.moviestreaming.domain.remote.usecase.user

import android.net.Uri
import br.com.hellodev.moviestreaming.domain.remote.repository.user.UserRepository

class SaveImageUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(uri: Uri): String {
        return repository.saveImage(uri)
    }

}