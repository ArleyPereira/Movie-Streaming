package br.com.hellodev.moviestreaming.presenter.features.profile.action

import android.net.Uri
import br.com.hellodev.moviestreaming.presenter.features.profile.parameter.EditProfileParameter

sealed class EditProfileAction {
    data object ClearFeedback : EditProfileAction()
    data object Update : EditProfileAction()
    data class OnNameChanged(val name: String) : EditProfileAction()
    data class OnSurnameChanged(val surname: String) : EditProfileAction()
    data class OnPhoneChanged(val phone: String) : EditProfileAction()
    data class SetOnBackResult(val parameter: EditProfileParameter) : EditProfileAction()
    data class SetImageUri(val uri: Uri?) : EditProfileAction()
}