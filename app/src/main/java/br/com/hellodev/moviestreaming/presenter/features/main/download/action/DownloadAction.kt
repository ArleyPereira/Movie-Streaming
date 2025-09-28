package br.com.hellodev.moviestreaming.presenter.features.main.download.action

import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

sealed class DownloadAction {
    object InitData : DownloadAction()
    object OnDeleteMovie : DownloadAction()
    object OnSearch : DownloadAction()

    data class OnQueryChanged(val query: String) : DownloadAction()
    data class OnSelectedMovie(val movie: Movie) : DownloadAction()
    data class SetCurrentBottomSheet(val type: SheetType) : DownloadAction()
}