package br.com.hellodev.moviestreaming.presenter.features.profile.action

sealed class EditProfileAction {
    data object Update : EditProfileAction()
    data class OnNameChanged(val name: String) : EditProfileAction()
    data class OnSurnameChanged(val surname: String) : EditProfileAction()
    data class OnPhoneChanged(val phone: String) : EditProfileAction()
    data class OnGenreChanged(val genre: String) : EditProfileAction()
    data class OnCountryChanged(val country: String) : EditProfileAction()
}