package br.com.hellodev.moviestreaming.presenter.screens.main.account.state

import br.com.hellodev.moviestreaming.domain.remote.model.User

data class AccountState(
    val isLoading: Boolean = true,
    val user: User? = null
)
