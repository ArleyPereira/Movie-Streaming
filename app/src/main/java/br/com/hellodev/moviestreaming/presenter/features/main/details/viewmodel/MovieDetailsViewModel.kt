package br.com.hellodev.moviestreaming.presenter.features.main.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.com.hellodev.moviestreaming.core.enums.dialog.DialogType
import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import br.com.hellodev.moviestreaming.core.navigation.routes.bar.BottomAppBarRoutes
import br.com.hellodev.moviestreaming.domain.remote.usecase.credits.GetMovieCreditsUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetMovieDetailsUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.reviews.GetMovieReviewsUseCase
import br.com.hellodev.moviestreaming.presenter.features.main.details.action.MovieDetailsAction
import br.com.hellodev.moviestreaming.presenter.features.main.details.state.MovieDetailsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    init {
        getMovieDetails()
    }

    fun submitAction(action: MovieDetailsAction) {
        when (action) {
            is MovieDetailsAction.SetCurrentDialog -> {
                setCurrentDialog(type = action.type)
            }

            MovieDetailsAction.StartDownload -> {
                startDownload()
            }
        }
    }

    private fun getMovieDetails() {
        viewModelScope.launch {
            val movieId = savedStateHandle.toRoute<BottomAppBarRoutes.Details>().id
            val response = getMovieDetailsUseCase(movieId)

            when (response.resultStatus) {
                ResultStatus.SUCCESS -> {
                    _state.update {
                        it.copy(
                            movie = response.results,
                            isLoading = false
                        )
                    }

                    getMovieCredits()
                }

                else -> {

                }
            }
        }
    }

    private fun getMovieCredits() {
        viewModelScope.launch {
            val movieId = savedStateHandle.toRoute<BottomAppBarRoutes.Details>().id
            val response = getMovieCreditsUseCase(movieId)

            when (response.resultStatus) {
                ResultStatus.SUCCESS -> {
                    _state.update {
                        it.copy(
                            credits = response.results,
                            isLoading = false
                        )
                    }

                    getMovieReviews()
                }

                else -> {

                }
            }

        }
    }

    private fun getMovieReviews() {
        viewModelScope.launch {
            val movieId = savedStateHandle.toRoute<BottomAppBarRoutes.Details>().id
            val response = getMovieReviewsUseCase(movieId)

            when (response.resultStatus) {
                ResultStatus.SUCCESS -> {
                    _state.update {
                        it.copy(
                            reviews = response.results,
                            isLoading = false
                        )
                    }
                }

                else -> {

                }
            }

        }
    }

    private fun startDownload() {
        viewModelScope.launch {
            if (_state.value.isDownloading) {
                _state.update {
                    it.copy(currentDialog = DialogType.DOWNLOADING_DIALOG)
                }
                return@launch
            }

            _state.update {
                it.copy(
                    currentDialog = DialogType.DOWNLOADING_DIALOG,
                    downloadProgress = 0,
                    downloadedSize = 0f,
                    isDownloading = true
                )
            }

            val totalSize = _state.value.movie?.runtime?.toFloat() ?: 0f
            for (progress in 1..100) {
                delay(50)
                val downloadedSize = totalSize * (progress / 100f)
                _state.update {
                    it.copy(
                        downloadProgress = progress,
                        downloadedSize = downloadedSize
                    )
                }
            }

            delay(500)
            _state.update {
                it.copy(
                    currentDialog = DialogType.EMPTY_DIALOG,
                    isDownloading = false
                )
            }
        }
    }

    private fun setCurrentDialog(type: DialogType) {
        _state.update { it.copy(currentDialog = type) }
    }

}