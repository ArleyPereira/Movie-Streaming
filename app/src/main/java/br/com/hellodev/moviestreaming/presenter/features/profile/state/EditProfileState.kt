package br.com.hellodev.moviestreaming.presenter.features.profile.state

import br.com.hellodev.moviestreaming.core.enums.input.InputType

data class EditProfileState(
    val isLoading: Boolean = false,
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val genre: String = "",
    val country: String = "",
    val hasFeedback: Boolean = false,
    val inputError: InputType? = null
)
