package br.com.hellodev.moviestreaming.data.remote.repository.user

import android.net.Uri
import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.domain.remote.model.user.User
import br.com.hellodev.moviestreaming.domain.remote.repository.user.UserRepository
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl : UserRepository {

    private val usersReference = FirebaseHelper
        .getDatabase()
        .child("users")

    private val profileImageRef = FirebaseHelper
        .getStorage()
        .child("images")
        .child(FirebaseHelper.getUserId())
        .child("profile_image.jpg")

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

    override suspend fun saveImage(uri: Uri): String {
        return suspendCoroutine { continuation ->
            val uploadTask = profileImageRef.putFile(uri)
            uploadTask.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    profileImageRef.downloadUrl.addOnSuccessListener { uri ->
                        continuation.resumeWith(Result.success(uri.toString()))
                    }
                } else {
                    task.exception?.let {
                        continuation.resumeWith(Result.failure(it))
                    }
                }
            }.addOnFailureListener { exception ->
                continuation.resumeWith(Result.failure(exception))
            }
        }
    }

}