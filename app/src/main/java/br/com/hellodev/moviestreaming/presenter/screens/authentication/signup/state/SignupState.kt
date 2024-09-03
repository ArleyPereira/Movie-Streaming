package br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.state

data class SignupState(
    val isLoading: Boolean = false,
    val email: String = "u@gmail.com",
    val password: String = "teste123",
    val passwordVisibility: Boolean = false,
    val enabledSignupButton: Boolean = false,
    val hasError: Boolean = false,
    val error: Int? = null
)
