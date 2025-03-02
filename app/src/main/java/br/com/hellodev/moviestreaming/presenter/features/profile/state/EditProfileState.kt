package br.com.hellodev.moviestreaming.presenter.features.profile.state

import android.net.Uri
import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.enums.input.InputType

data class EditProfileState(
    val isLoadingScreen: Boolean = false,
    val isLoadingImage: Boolean = false,
    val isLoading: Boolean = false,
    val photo: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phone: String = "",
    val genre: String? = null,
    val country: String? = null,
    val urlImageProfile: String? = null,
    val hasFeedback: Boolean = false,
    val inputError: InputType? = null,
    val feedbackUI: Pair<FeedbackType, Int>? = null,
    val imageUri: Uri = Uri.EMPTY
)
