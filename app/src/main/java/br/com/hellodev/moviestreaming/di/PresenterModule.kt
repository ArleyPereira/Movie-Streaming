package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.presenter.features.authentication.login.viewmodel.LoginViewModel
import br.com.hellodev.moviestreaming.presenter.features.authentication.signup.viewmodel.SignupViewModel
import br.com.hellodev.moviestreaming.presenter.features.main.account.viewmodel.AccountViewModel
import br.com.hellodev.moviestreaming.presenter.features.main.download.viewModel.DownloadViewModel
import br.com.hellodev.moviestreaming.presenter.features.main.favorite.viewmodel.FavoriteViewModel
import br.com.hellodev.moviestreaming.presenter.features.main.home.viewmodel.HomeViewModel
import br.com.hellodev.moviestreaming.presenter.features.main.search.viewmodel.SearchViewModel
import br.com.hellodev.moviestreaming.presenter.features.profile.viewmodel.EditProfileViewModel
import br.com.hellodev.moviestreaming.presenter.features.splash.viewmodel.SplashViewModel
import br.com.hellodev.moviestreaming.presenter.features.welcome.viewmodel.WelcomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presenterModule = module {

    viewModelOf(::SignupViewModel)

    viewModelOf(::LoginViewModel)

    viewModelOf(::WelcomeViewModel)

    viewModelOf(::SplashViewModel)

    viewModelOf(::HomeViewModel)

    viewModelOf(::SearchViewModel)

    viewModelOf(::FavoriteViewModel)

    viewModelOf(::DownloadViewModel)

    viewModelOf(::AccountViewModel)

    viewModelOf(::EditProfileViewModel)

}