package br.com.hellodev.moviestreaming.data.remote.repository.download

import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.domain.remote.repository.download.DownloadRepository
import kotlin.coroutines.suspendCoroutine

class DownloadRepositoryImpl: DownloadRepository {

    private val downloadReference = FirebaseHelper
        .getDatabase()
        .child("downloads")

    override suspend fun save(movie: Movie) {
        suspendCoroutine { continuation ->
            downloadReference
                .child(FirebaseHelper.getUserId())
                .child(movie.id.toString())
                .setValue(movie)
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