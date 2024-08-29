package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.data.remote.repository.authentication.SignupRepositoryImpl
import br.com.hellodev.moviestreaming.domain.remote.repository.authentication.SignupRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SignupRepository> { SignupRepositoryImpl(auth = get()) }

}