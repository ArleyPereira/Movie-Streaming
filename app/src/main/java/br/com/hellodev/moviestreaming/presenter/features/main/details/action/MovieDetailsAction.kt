package br.com.hellodev.moviestreaming.presenter.features.main.details.action

import br.com.hellodev.moviestreaming.core.enums.dialog.DialogType

sealed class MovieDetailsAction {

    object StartDownload: MovieDetailsAction()

    data class SetCurrentDialog(val type: DialogType): MovieDetailsAction()

}