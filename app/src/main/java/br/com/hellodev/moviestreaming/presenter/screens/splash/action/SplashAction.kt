package br.com.hellodev.moviestreaming.presenter.screens.splash.action

sealed class SplashAction {
    data object OnNextScreen : SplashAction()
}