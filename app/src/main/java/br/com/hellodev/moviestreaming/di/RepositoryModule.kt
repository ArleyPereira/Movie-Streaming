package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.data.remote.repository.authentication.AuthenticationRepositoryImpl
import br.com.hellodev.moviestreaming.data.remote.repository.download.DownloadRepositoryImpl
import br.com.hellodev.moviestreaming.data.remote.repository.movie.MovieRepositoryImpl
import br.com.hellodev.moviestreaming.data.remote.repository.user.UserRepositoryImpl
import br.com.hellodev.moviestreaming.domain.remote.repository.authentication.AuthenticationRepository
import br.com.hellodev.moviestreaming.domain.remote.repository.download.DownloadRepository
import br.com.hellodev.moviestreaming.domain.remote.repository.movie.MovieRepository
import br.com.hellodev.moviestreaming.domain.remote.repository.user.UserRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {

    factoryOf(::AuthenticationRepositoryImpl).bind(AuthenticationRepository::class)

    factoryOf(::UserRepositoryImpl).bind(UserRepository::class)

    factoryOf(::MovieRepositoryImpl).bind(MovieRepository::class)

    factoryOf(::DownloadRepositoryImpl).bind(DownloadRepository::class)

}