package br.com.hellodev.moviestreaming.presenter.screens.main.account.action

sealed class AccountAction {
    data object Logout : AccountAction()
}