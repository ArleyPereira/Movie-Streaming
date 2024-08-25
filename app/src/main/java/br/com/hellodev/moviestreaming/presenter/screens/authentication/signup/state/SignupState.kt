package br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.state

data class SignupState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val enabledSignupButton: Boolean = false
)
