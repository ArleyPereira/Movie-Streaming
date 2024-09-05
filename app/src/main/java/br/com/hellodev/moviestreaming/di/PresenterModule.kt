package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.presenter.screens.authentication.login.viewmodel.LoginViewModel
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.viewmodel.SignupViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presenterModule = module {

    viewModel {
        SignupViewModel(
            registerUseCase = get(),
            saveUserUseCase = get()
        )
    }

    viewModel {
        LoginViewModel()
    }

}