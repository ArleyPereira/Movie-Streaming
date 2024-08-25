package br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.action

import br.com.hellodev.moviestreaming.core.enums.InputType

sealed class SignupAction {

    data class OnValueChange(
        val value: String,
        val type: InputType
    ) : SignupAction()

}