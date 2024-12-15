package br.com.hellodev.moviestreaming.presenter.features.profile.state

data class EditProfileState(
    val isLoading: Boolean = false,
    val name: String = "",
    val surname: String = "",
    val phone: String = ""
)
