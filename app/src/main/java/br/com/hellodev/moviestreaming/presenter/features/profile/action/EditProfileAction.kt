package br.com.hellodev.moviestreaming.presenter.features.profile.action

import br.com.hellodev.moviestreaming.presenter.features.profile.parameter.EditProfileParameter

sealed class EditProfileAction {
    data object Update : EditProfileAction()
    data class OnNameChanged(val name: String) : EditProfileAction()
    data class OnSurnameChanged(val surname: String) : EditProfileAction()
    data class OnPhoneChanged(val phone: String) : EditProfileAction()
    data class SetOnBackResult(val parameter: EditProfileParameter) : EditProfileAction()
}