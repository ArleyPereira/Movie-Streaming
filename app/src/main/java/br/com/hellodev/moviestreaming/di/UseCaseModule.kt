package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.domain.remote.usecase.authentication.RegisterUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { RegisterUseCase(repository = get()) }

}