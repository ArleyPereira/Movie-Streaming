package br.com.hellodev.moviestreaming.presenter.screens.authentication.login.state

import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val enabledSignInButton: Boolean = false,
    val hasFeedback: Boolean = false,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
    val isAuthenticated: Boolean = false
)
