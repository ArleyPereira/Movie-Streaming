package br.com.hellodev.moviestreaming.presenter.features.main.account.state

import br.com.hellodev.moviestreaming.domain.remote.model.user.User

data class AccountState(
    val isLoading: Boolean = true,
    val user: User? = null
)
