package br.com.hellodev.moviestreaming.presenter.screens.main.favorite.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.presenter.screens.main.favorite.action.FavoriteAction
import br.com.hellodev.moviestreaming.presenter.screens.main.favorite.state.FavoriteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteViewModel: ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    fun submitAction(action: FavoriteAction) {
    }

}