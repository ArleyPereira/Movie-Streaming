package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.data.remote.SignupRepositoryImpl
import br.com.hellodev.moviestreaming.domain.remote.SignupRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<SignupRepository> { SignupRepositoryImpl() }

}