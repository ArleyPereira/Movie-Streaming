package br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.viewmodel

import androidx.lifecycle.ViewModel
import br.com.hellodev.moviestreaming.core.enums.InputType
import br.com.hellodev.moviestreaming.core.enums.InputType.*
import br.com.hellodev.moviestreaming.core.functions.isValidEmail
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.action.SignupAction
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.state.SignupState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    fun submitAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is SignupAction.OnPasswordVisibilityChange -> {
                onPasswordVisibilityChange()
            }
        }
    }

    private fun onValueChange(value: String, type: InputType) {
        when (type) {
            EMAIL -> {
                onEmailChange(value)
            }

            PASSWORD -> {
                onPasswordChange(value)
            }
        }

        enabledSignupButton()
    }

    private fun onEmailChange(value: String) {
        _state.update { currentState ->
            currentState.copy(email = value)
        }
    }

    private fun onPasswordChange(value: String) {
        _state.update { currentState ->
            currentState.copy(password = value)
        }
    }

    private fun onPasswordVisibilityChange() {
        _state.update { currentState ->
            currentState.copy(passwordVisibility = !currentState.passwordVisibility)
        }
    }

    private fun enabledSignupButton() {
        val emailValid = isValidEmail(_state.value.email)
        val passwordValid = _state.value.password.isNotBlank()

        _state.update { currentState ->
            currentState.copy(enabledSignupButton = emailValid && passwordValid)
        }
    }

}