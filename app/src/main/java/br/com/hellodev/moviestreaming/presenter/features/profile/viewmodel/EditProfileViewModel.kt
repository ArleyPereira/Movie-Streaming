package br.com.hellodev.moviestreaming.presenter.features.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.core.functions.isValidName
import br.com.hellodev.moviestreaming.core.functions.isValidPhone
import br.com.hellodev.moviestreaming.presenter.features.profile.action.EditProfileAction
import br.com.hellodev.moviestreaming.presenter.features.profile.parameter.EditProfileParameter
import br.com.hellodev.moviestreaming.presenter.features.profile.state.EditProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {

    private val _state = MutableStateFlow(EditProfileState())
    val state = _state.asStateFlow()

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
        }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            if (!isValidProfile()) {
                inputFeedbackError()
                return@launch
            }

            // Salvar esses dados no firebase
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
                currentState.copy(genre = it)
            }
        }

        parameter.country?.let {
            _state.update { currentState ->
                currentState.copy(country = it)
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

}