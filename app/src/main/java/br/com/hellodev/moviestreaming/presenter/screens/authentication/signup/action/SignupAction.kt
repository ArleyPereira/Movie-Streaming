package br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.action

import br.com.hellodev.moviestreaming.core.enums.input.InputType

sealed class SignupAction {

    data class OnValueChange(
        val value: String,
        val type: InputType
    ) : SignupAction()

    data object OnPasswordVisibilityChange : SignupAction()

    data object OnSignup : SignupAction()

    data object ResetError : SignupAction()

}