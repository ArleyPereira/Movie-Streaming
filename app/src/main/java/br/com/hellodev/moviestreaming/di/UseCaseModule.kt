package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.LoginUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.RegisterUseCase
import br.com.hellodev.moviestreaming.domain.remote.usecase.user.SaveUserUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { RegisterUseCase(repository = get()) }

    factory { LoginUseCase(repository = get()) }

    factory { SaveUserUseCase(repository = get()) }

}