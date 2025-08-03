package br.com.hellodev.moviestreaming.presenter.features.main.search.action

sealed class SearchAction {
    object OnSearch : SearchAction()
    data class OnQueryChanged(val query: String) : SearchAction()
}