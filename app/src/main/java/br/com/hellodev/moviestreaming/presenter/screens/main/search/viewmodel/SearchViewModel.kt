package br.com.hellodev.moviestreaming.presenter.screens.main.search.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.presenter.screens.main.search.action.SearchAction
import br.com.hellodev.moviestreaming.presenter.screens.main.search.state.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun submitAction(action: SearchAction) {
    }

}