package br.com.hellodev.moviestreaming.presenter.features.profile.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.core.functions.isValidName
import br.com.hellodev.moviestreaming.core.functions.isValidPhone
import br.com.hellodev.moviestreaming.domain.remote.model.user.User
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.GetUserUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.SaveUserUseCase
import br.com.hellodev.moviestreaming.presenter.features.profile.action.EditProfileAction
import br.com.hellodev.moviestreaming.presenter.features.profile.parameter.EditProfileParameter
import br.com.hellodev.moviestreaming.presenter.features.profile.state.EditProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileState())
    val state = _state.asStateFlow()

    init {
        getUser()
    }

    fun submitAction(action: EditProfileAction) {
        when (action) {
            is EditProfileAction.Update -> {
                updateProfile()
            }

            is EditProfileAction.OnNameChanged -> {
                onNameChanged(name = action.name)
            }

            is EditProfileAction.OnPhoneChanged -> {
                onPhoneChanged(phone = action.phone)
            }

            is EditProfileAction.OnSurnameChanged -> {
                onSurnameChanged(surname = action.surname)
            }

            is EditProfileAction.SetOnBackResult -> {
                setOnBackResult(parameter = action.parameter)
            }

            is EditProfileAction.ClearFeedback -> {
                clearFeedback()
            }

            is EditProfileAction.SetImageUri -> {
                setImageUri(uri = action.uri)
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(isLoadingScreen = true)
            }

            val user = getUserUseCase()

            _state.update { currentState ->
                currentState.copy(
                    name = user.name ?: "",
                    surname = user.surname ?: "",
                    email = user.email ?: "",
                    phone = user.phone ?: "",
                    genre = user.genre ?: "",
                    country = user.country ?: "",
                    isLoadingScreen = false
                )
            }
        }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            if (!isValidProfile()) {
                inputFeedbackError()
                return@launch
            }

            _state.update { currentState ->
                currentState.copy(isLoading = true)
            }

            val user = User(
                name = _state.value.name,
                surname = _state.value.surname,
                email = _state.value.email,
                phone = _state.value.phone,
                genre = _state.value.genre,
                country = _state.value.country
            )

            saveUserUseCase(user = user)

            _state.update { currentState ->
                currentState.copy(
                    hasFeedback = true,
                    isLoading = false,
                    feedbackUI = Pair(
                        FeedbackType.SUCCESS,
                        R.string.success_save_user_generic
                    )
                )
            }
        }
    }

    private fun onNameChanged(name: String) {
        _state.update { currentState ->
            currentState.copy(
                name = name.trim(),
                inputError = null
            )
        }
    }

    private fun onSurnameChanged(surname: String) {
        _state.update { currentState ->
            currentState.copy(
                surname = surname.trim(),
                inputError = null
            )
        }
    }

    private fun onPhoneChanged(phone: String) {
        _state.update { currentState ->
            currentState.copy(
                phone = phone,
                inputError = null
            )
        }
    }

    private fun setOnBackResult(parameter: EditProfileParameter) {
        parameter.genre?.let {
            _state.update { currentState ->
                currentState.copy(genre = it.name)
            }
        }

        parameter.country?.let {
            _state.update { currentState ->
                currentState.copy(country = it.name)
            }
        }

        clearError()
    }

    private fun clearError() {
        _state.update { currentState ->
            currentState.copy(inputError = null)
        }
    }

    private fun inputFeedbackError() {
        val inputError = when {
            !isValidName(_state.value.name) -> InputType.FIRST_NAME
            !isValidName(_state.value.surname) -> InputType.SURNAME
            !isValidPhone(_state.value.phone) -> InputType.PHONE
            _state.value.genre == null -> InputType.GENRE
            _state.value.country == null -> InputType.COUNTRY
            else -> null
        }

        _state.update { currentState ->
            currentState.copy(inputError = inputError)
        }
    }

    private fun isValidProfile(): Boolean {
        val name = isValidName(_state.value.name)
        val surname = isValidName(_state.value.surname)
        val phone = isValidPhone(_state.value.phone)
        val genre = _state.value.genre != null
        val country = _state.value.country != null

        return name && surname && phone && genre && country
    }

    private fun clearFeedback() {
        _state.update { currentState ->
            currentState.copy(hasFeedback = false, feedbackUI = null)
        }
    }

    private fun setImageUri(uri: Uri?) {
        _state.update { currentState ->
            currentState.copy(imageUri = uri)
        }

    }

}