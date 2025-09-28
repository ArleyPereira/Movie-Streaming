package br.com.hellodev.moviestreaming.presenter.features.main.download.state

import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType
import br.com.hellodev.moviestreaming.domain.remote.model.movie.MovieDownload

data class DownloadState(
    val isLoading: Boolean = true,
    val movies: List<MovieDownload> = emptyList(),
    val moviesFiltered: List<MovieDownload> = emptyList(),
    val selectedMovie: MovieDownload? = null,
    val query: String = "",
    val sheetType: SheetType = SheetType.EMPTY_BOTTOM_SHEET
)
