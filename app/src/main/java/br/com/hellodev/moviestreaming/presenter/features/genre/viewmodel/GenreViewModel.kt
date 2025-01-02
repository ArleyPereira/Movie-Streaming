package br.com.hellodev.moviestreaming.presenter.features.genre.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.domain.remote.model.genre.Genre
import br.com.hellodev.moviestreaming.presenter.features.genre.action.GenreAction
import br.com.hellodev.moviestreaming.presenter.features.genre.state.GenreState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GenreViewModel : ViewModel() {

    private val _state = MutableStateFlow(GenreState())
    val state = _state.asStateFlow()

    init {
        getGenres()
    }

    fun submitAction(action: GenreAction) {
        when (action) {
            is GenreAction.OnGenreSelected -> {
                onGenreSelected(genre = action.genre)
            }
        }
    }

    private fun onGenreSelected(genre: Genre) {
        _state.update { currentState ->
            currentState.copy(selectedGenre = genre)
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(genres = Genre.items)
            }
        }
    }

}