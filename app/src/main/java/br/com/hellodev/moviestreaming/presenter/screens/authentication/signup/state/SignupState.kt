package br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.state

data class SignupState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)
