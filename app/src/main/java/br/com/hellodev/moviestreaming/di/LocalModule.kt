package br.com.hellodev.moviestreaming.di

import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import org.koin.dsl.module

val localModule = module {
    single<AppPreferences> {
        AppPreferences(context = get())
    }
}