package br.com.hellodev.moviestreaming.presenter.features.welcome.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import br.com.hellodev.moviestreaming.presenter.features.welcome.action.WelcomeAction
import br.com.hellodev.moviestreaming.presenter.features.welcome.state.WelcomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WelcomeViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(WelcomeState())
    val state = _state.asStateFlow()

    fun submitAction(action: WelcomeAction) {
        when (action) {
            is WelcomeAction.OnNextScreen -> {
                saveWelcomeVisited()
            }
        }
    }

    private fun saveWelcomeVisited() {
        appPreferences.saveWelcomeVisited(true)
        _state.update { currentState ->
            currentState.copy(nextScreen = true)
        }
    }

}