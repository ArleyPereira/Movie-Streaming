package br.com.hellodev.moviestreaming.presenter.features.profile.parameter

import br.com.hellodev.moviestreaming.domain.remote.model.country.Country
import br.com.hellodev.moviestreaming.domain.remote.model.genre.GenreUser
import kotlinx.serialization.Serializable

@Serializable
data class EditProfileParameter(
    val genreUser: GenreUser? = null,
    val country: Country? = null
)
