package br.com.hellodev.moviestreaming.presenter.features.main.details.state

import br.com.hellodev.moviestreaming.core.enums.dialog.DialogType
import br.com.hellodev.moviestreaming.domain.remote.model.credits.Credits
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.domain.remote.model.reviews.Review

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
    val credits: Credits? = null,
    val reviews: List<Review>? = null,
    val downloadProgress: Int = 0,
    val downloadedSize: Float = 0f,
    val currentDialog: DialogType = DialogType.EMPTY_DIALOG,
    val isDownloading: Boolean = false
)
