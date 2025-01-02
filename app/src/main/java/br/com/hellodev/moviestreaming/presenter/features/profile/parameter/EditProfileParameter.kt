package br.com.hellodev.moviestreaming.presenter.features.profile.parameter

import br.com.hellodev.moviestreaming.domain.remote.model.country.Country
import br.com.hellodev.moviestreaming.domain.remote.model.genre.Genre
import kotlinx.serialization.Serializable

@Serializable
data class EditProfileParameter(
    val genre: Genre? = null,
    val country: Country? = null
)
