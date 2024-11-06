package br.com.hellodev.moviestreaming.presenter.features.main.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.helper.FirebaseHelper
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.GetUserUseCase
import br.com.hellodev.moviestreaming.presenter.features.main.account.action.AccountAction
import br.com.hellodev.moviestreaming.presenter.features.main.account.state.AccountState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountViewModel(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AccountState())
    val state = _state.asStateFlow()

    init {
        getUser()
    }

    fun submitAction(action: AccountAction) {
        when (action) {
            AccountAction.Logout -> {
                logout()
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            val user = getUserUseCase()

            _state.value = _state.value.copy(
                user = user,
                isLoading = false
            )
        }
    }

    private fun logout() {
        FirebaseHelper.getAuth().signOut()
    }

}