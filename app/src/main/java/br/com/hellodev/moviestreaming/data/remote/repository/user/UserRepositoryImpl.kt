package br.com.hellodev.moviestreaming.data.remote.repository.user

import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.domain.remote.model.User
import br.com.hellodev.moviestreaming.domain.remote.repository.user.UserRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl : UserRepository {

    private val usersReference = FirebaseHelper
        .getDatabase()
        .child("users")

    override suspend fun save(user: User) {
        suspendCoroutine { continuation ->
            usersReference
                .child(FirebaseHelper.getUserId())
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

    override suspend fun getUser(): User {
        return suspendCoroutine { continuation ->
            usersReference
                .child(FirebaseHelper.getUserId())
                .get()
                .addOnCompleteListener { task ->
                    val user = task.result.getValue(User::class.java)
                    user?.let {
                        continuation.resumeWith(Result.success(it))
                    } ?: run {
                        continuation.resumeWith(Result.failure(Exception("User not found")))
                    }
                }
        }
    }

}