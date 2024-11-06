package br.com.hellodev.moviestreaming.presenter.features.main.account.action

sealed class AccountAction {
    data object Logout : AccountAction()
}