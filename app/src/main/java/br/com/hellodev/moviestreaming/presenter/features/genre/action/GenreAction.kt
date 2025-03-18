package br.com.hellodev.moviestreaming.presenter.features.genre.action

import br.com.hellodev.moviestreaming.domain.remote.model.genre.GenreUser

sealed class GenreAction {
    data class OnGenreSelected(val genreUser: GenreUser) : GenreAction()
}