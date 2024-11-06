package br.com.hellodev.moviestreaming.presenter.features.profile.action

sealed class EditProfileAction {
    data object Update : EditProfileAction()
}