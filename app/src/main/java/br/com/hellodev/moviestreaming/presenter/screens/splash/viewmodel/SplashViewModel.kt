package br.com.hellodev.moviestreaming.presenter.screens.splash.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import br.com.hellodev.moviestreaming.presenter.screens.splash.action.SplashAction
import br.com.hellodev.moviestreaming.presenter.screens.splash.state.SplashState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SplashViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    fun submitAction(action: SplashAction) {
        when (action) {
            is SplashAction.OnNextScreen -> {
                getWelcomeVisited()
            }
        }
    }

    private fun getWelcomeVisited() {
        _state.update { currentState ->
            currentState.copy(
                isWelcomeVisited = appPreferences.getWelcomeVisited(),
                isLoading = false,
                isAuthenticated = FirebaseHelper.isAuthenticated()
            )
        }
    }

}