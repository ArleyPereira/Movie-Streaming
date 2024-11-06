package br.com.hellodev.moviestreaming.presenter.features.authentication.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.core.enums.input.InputType.EMAIL
import br.com.hellodev.moviestreaming.core.enums.input.InputType.PASSWORD
import br.com.hellodev.moviestreaming.core.functions.isValidEmail
import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.domain.remote.model.User
import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.RegisterUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.SaveUserUseCase
import br.com.hellodev.moviestreaming.presenter.features.authentication.signup.action.SignupAction
import br.com.hellodev.moviestreaming.presenter.features.authentication.signup.state.SignupState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignupViewModel(
    private val registerUseCase: RegisterUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

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

            is SignupAction.OnSignup -> {
                onSignup()
            }

            is SignupAction.ResetError -> {
                resetError()
            }
        }
    }

    private fun onSignup() {
        viewModelScope.launch {
            try {
                registerUseCase(
                    email = _state.value.email,
                    password = _state.value.password
                )

                saveUserUseCase(user = User(email = _state.value.email))

                _state.update { currentState ->
                    currentState.copy(isAuthenticated = true)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()

                _state.update { currentState ->
                    currentState.copy(
                        hasError = true,
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

    private fun resetError() {
        _state.update { currentState ->
            currentState.copy(hasError = false, feedbackUI = null)
        }
    }

}