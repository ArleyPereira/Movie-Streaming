package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.LoginUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.RegisterUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.movie.GetNowPlayingUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.GetUserUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.SaveImageUserUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.SaveUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {

    factoryOf(::RegisterUseCase)

    factoryOf(::LoginUseCase)

    factoryOf(::SaveUserUseCase)

    factoryOf(::GetUserUseCase)

    factoryOf(::SaveImageUserUseCase)

    factoryOf(::GetNowPlayingUseCase)

}