package br.com.hellodev.moviestreaming.presenter.features.genre.action

import br.com.hellodev.moviestreaming.domain.remote.model.genre.Genre

sealed class GenreAction {
    data class OnGenreSelected(val genre: Genre) : GenreAction()
}