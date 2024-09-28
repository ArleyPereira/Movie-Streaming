package br.com.hellodev.moviestreaming.presenter.screens.authentication.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.core.enums.input.InputType.EMAIL
import br.com.hellodev.moviestreaming.core.enums.input.InputType.PASSWORD
import br.com.hellodev.moviestreaming.core.functions.isValidEmail
import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.LoginUseCase
import br.com.hellodev.moviestreaming.presenter.screens.authentication.login.action.LoginAction
import br.com.hellodev.moviestreaming.presenter.screens.authentication.login.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun submitAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnValueChange -> {
                onValueChange(action.value, action.type)
            }

            is LoginAction.OnPasswordVisibilityChange -> {
                onPasswordVisibilityChange()
            }

            is LoginAction.OnSignIn -> {
                onSignIn()
            }

            is LoginAction.ResetError -> {
                resetError()
            }
        }
    }

    private fun onSignIn() {
        viewModelScope.launch {
            try {
                loginUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )

                _state.update { currentState ->
                    currentState.copy(isAuthenticated = true)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update { currentState ->
                    currentState.copy(
                        hasFeedback = true,
                        feedbackUI = Pair(
                            FeedbackType.ERROR,
                            FirebaseHelper.validError(exception.message)
                        )
                    )
                }
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

        enabledSignInButton()
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

    private fun enabledSignInButton() {
        val emailValid = isValidEmail(_state.value.email)
        val passwordValid = _state.value.password.isNotBlank()

        _state.update { currentState ->
            currentState.copy(enabledSignInButton = emailValid && passwordValid)
        }
    }

    private fun resetError() {
        _state.update { currentState ->
            currentState.copy(hasFeedback = false, feedbackUI = null)
        }
    }

}