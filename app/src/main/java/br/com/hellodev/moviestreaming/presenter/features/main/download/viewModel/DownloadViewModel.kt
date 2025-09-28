package br.com.hellodev.moviestreaming.presenter.features.main.download.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType
import br.com.hellodev.moviestreaming.domain.remote.model.movie.MovieDownload
import br.com.hellodev.moviestreaming.domain.remote.usecase.download.DeleteMovieUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.download.GetMoviesUseCase
import br.com.hellodev.moviestreaming.presenter.features.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.features.main.download.state.DownloadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DownloadViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DownloadState())
    val state = _state.asStateFlow()

    fun submitAction(action: DownloadAction) {
        when (action) {
            is DownloadAction.InitData -> {
                getMovies()
            }

            is DownloadAction.OnSelectedMovie -> {
                onSelectedMovie(action.movie)
            }

            is DownloadAction.SetCurrentBottomSheet -> {
                setCurrentBottomSheet(action.type)
            }

            is DownloadAction.OnDeleteMovie -> {
                deleteMovie()
            }

            is DownloadAction.OnQueryChanged -> {
                onQueryChanged(action.query)
            }

            is DownloadAction.OnSearch -> {
                onSearch()
            }
        }
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = getMoviesUseCase()

                _state.update {
                    it.copy(
                        isLoading = false,
                        movies = movies,
                        moviesFiltered = movies
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun deleteMovie() {
        viewModelScope.launch {
            try {
                deleteMovieUseCase(movie = _state.value.selectedMovie)
                val newMovies = _state.value.movies.toMutableList().apply {
                    remove(_state.value.selectedMovie)
                }

                val newMoviesFiltered = _state.value.moviesFiltered.toMutableList().apply {
                    remove(_state.value.selectedMovie)
                }

                _state.update {
                    it.copy(
                        isLoading = false,
                        movies = newMovies,
                        moviesFiltered = newMoviesFiltered,
                        selectedMovie = null,
                        sheetType = SheetType.EMPTY_BOTTOM_SHEET
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun onQueryChanged(query: String) {
        _state.update { currentState ->
            currentState.copy(query = query)
        }
    }

    private fun onSearch() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isLoading = true)
            }

            if (_state.value.query.isEmpty()) {
                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        moviesFiltered = _state.value.movies
                    )
                }
            } else {
                val newMoviesFiltered = _state.value.movies.filter { movie ->
                    movie.title?.contains(_state.value.query, ignoreCase = true) == true
                }

                _state.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        moviesFiltered = newMoviesFiltered
                    )
                }
            }
        }
    }

    private fun onSelectedMovie(movie: MovieDownload) {
        _state.update {
            it.copy(
                selectedMovie = movie,
                sheetType = SheetType.DELETE_BOTTOM_SHEET
            )
        }
    }

    private fun setCurrentBottomSheet(type: SheetType) {
        _state.update { it.copy(sheetType = type) }
    }

}