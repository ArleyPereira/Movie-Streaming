package br.com.hellodev.moviestreaming.presenter.features.main.download.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.domain.remote.usecase.download.GetMoviesUseCase
import br.com.hellodev.moviestreaming.presenter.features.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.features.main.download.state.DownloadState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DownloadViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DownloadState())
    val state = _state.asStateFlow()

    init {
        getMovies()
    }

    fun submitAction(action: DownloadAction) {
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val movies = getMoviesUseCase()

                _state.update {
                    it.copy(
                        isLoading = false,
                        movies = movies
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}