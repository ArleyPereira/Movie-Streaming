package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.presenter.screens.authentication.login.viewmodel.LoginViewModel
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.viewmodel.SignupViewModel
import br.com.hellodev.moviestreaming.presenter.screens.splash.viewmodel.SplashViewModel
import br.com.hellodev.moviestreaming.presenter.screens.welcome.viewmodel.WelcomeViewModel
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
        LoginViewModel(
            loginUseCase = get()
        )
    }

    viewModel {
        WelcomeViewModel(
            appPreferences = get()
        )
    }

    viewModel {
        SplashViewModel(
            appPreferences = get()
        )
    }

}