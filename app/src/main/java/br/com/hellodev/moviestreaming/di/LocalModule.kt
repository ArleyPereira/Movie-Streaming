package br.com.hellodev.moviestreaming.di

import androidx.room.Room
import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import br.com.hellodev.moviestreaming.data.local.db.AppDatabase
import org.koin.dsl.module

val localModule = module {

    single<AppPreferences> {
        AppPreferences(context = get())
    }

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().movieDao() }

}