package br.com.hellodev.moviestreaming.data.remote.repository.user

import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.domain.remote.model.User
import br.com.hellodev.moviestreaming.domain.remote.repository.user.UserRepository
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl : UserRepository {

    private val usersReference = FirebaseHelper
        .getDatabase()
        .child("users")
        .child(FirebaseHelper.getUserId())

    override suspend fun save(user: User) {
        suspendCoroutine { continuation ->
            usersReference
                .setValue(user)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let { exception ->
                            continuation.resumeWith(Result.failure(exception))
                        }
                    }
                }
        }
    }

}