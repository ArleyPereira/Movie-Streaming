package br.com.hellodev.moviestreaming.presenter.features.profile.state

import br.com.hellodev.moviestreaming.core.enums.input.InputType
import br.com.hellodev.moviestreaming.domain.remote.model.country.Country
import br.com.hellodev.moviestreaming.domain.remote.model.genre.Genre

data class EditProfileState(
    val isLoading: Boolean = false,
    val name: String = "",
    val surname: String = "",
    val phone: String = "",
    val genre: Genre? = null,
    val country: Country? = null,
    val hasFeedback: Boolean = false,
    val inputError: InputType? = null
)
