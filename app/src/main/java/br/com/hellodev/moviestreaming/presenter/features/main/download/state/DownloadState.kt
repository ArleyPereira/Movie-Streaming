package br.com.hellodev.moviestreaming.presenter.features.main.download.state

import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie

data class DownloadState(
    val isLoading: Boolean = true,
    val movies: List<Movie> = emptyList(),
    val selectedMovie: Movie? = null,
    val sheetType: SheetType = SheetType.EMPTY_BOTTOM_SHEET
)
