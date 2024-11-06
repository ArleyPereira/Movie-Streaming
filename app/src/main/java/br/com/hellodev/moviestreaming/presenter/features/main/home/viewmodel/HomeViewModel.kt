package br.com.hellodev.moviestreaming.presenter.features.main.home.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.presenter.features.main.home.action.HomeAction
import br.com.hellodev.moviestreaming.presenter.features.main.home.state.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun submitAction(action: HomeAction) {

    }

}