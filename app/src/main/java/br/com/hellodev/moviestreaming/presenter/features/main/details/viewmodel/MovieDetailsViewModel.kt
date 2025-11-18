package br.com.hellodev.moviestreaming.presenter.features.main.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.com.hellodev.moviestreaming.core.enums.dialog.DialogType
import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import br.com.hellodev.moviestreaming.core.navigation.routes.bar.BottomAppBarRoutes
import br.com.hellodev.moviestreaming.domain.remote.model.favorite.MovieFavorite
import br.com.hellodev.moviestreaming.domain.remote.model.movie.MovieDownload
import br.com.hellodev.moviestreaming.domain.remote.usecase.credits.GetMovieCreditsUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.download.SaveMovieUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.favorite.DeleteFavoriteUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.favorite.GetFavoriteByIdUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.favorite.InsertFavoriteUseCase
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
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val getFavoriteByIdUseCase: GetFavoriteByIdUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
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

            is MovieDetailsAction.StartDownload -> {
                startDownload()
            }

            is MovieDetailsAction.OnFavoriteChange -> {
                onFavoriteChange()
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

                    getFavorite()
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

    private fun getFavorite() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    movieFavorite = getFavoriteByIdUseCase(_state.value.movie?.id ?: 0)
                )
            }
        }
    }

    private fun onFavoriteChange() {
        viewModelScope.launch {
            val movieFavorite = _state.value.movieFavorite
            if (movieFavorite != null) {
                deleteFavoriteUseCase(movieFavorite)

                _state.update { it.copy(movieFavorite = null) }
            } else {
                val movie = _state.value.movie
                val movieFavorite = MovieFavorite(
                    movieId = movie?.id,
                    title = movie?.title,
                    backdropPath = movie?.backdropPath,
                    runtime = movie?.runtime
                )

                insertFavoriteUseCase(movieFavorite)

                _state.update {
                    it.copy(movieFavorite = getFavoriteByIdUseCase(movie?.id ?: 0))
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

            val movieDownload = MovieDownload(
                id = _state.value.movie?.id,
                backdropPath = _state.value.movie?.backdropPath,
                title = _state.value.movie?.title,
                runtime = _state.value.movie?.runtime
            )

            _state.value.movie?.let { saveMovieUseCase(movie = movieDownload) }
        }
    }

    private fun setCurrentDialog(type: DialogType) {
        _state.update { it.copy(currentDialog = type) }
    }

}