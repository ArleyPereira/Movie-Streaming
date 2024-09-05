package br.com.hellodev.moviestreaming.presenter.screens.authentication.login.state

import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "u@gmail.com",
    val password: String = "teste123",
    val passwordVisibility: Boolean = false,
    val enabledSignInButton: Boolean = false,
    val hasError: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null
)
