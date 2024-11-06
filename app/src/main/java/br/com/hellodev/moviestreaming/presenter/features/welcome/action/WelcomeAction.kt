package br.com.hellodev.moviestreaming.presenter.features.welcome.action

sealed class WelcomeAction {
    data object OnNextScreen : WelcomeAction()
}