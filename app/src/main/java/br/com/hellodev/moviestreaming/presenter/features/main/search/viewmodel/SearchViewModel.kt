package br.com.hellodev.moviestreaming.presenter.features.main.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.SearchMovieUseCase
import br.com.hellodev.moviestreaming.presenter.features.main.search.action.SearchAction
import br.com.hellodev.moviestreaming.presenter.features.main.search.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun submitAction(action: SearchAction) {
        when (action) {
            is SearchAction.OnQueryChanged -> {
                onQueryChanged(action.query)
            }

            is SearchAction.OnSearch -> {
                onSearch()
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
            val response = searchMovieUseCase(_state.value.query)

            when (response.resultStatus) {
                ResultStatus.SUCCESS -> {

                }

                else -> {

                }
            }
        }
    }

}