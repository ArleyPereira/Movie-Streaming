package br.com.hellodev.moviestreaming.di

import androidx.room.Room
import br.com.hellodev.moviestreaming.core.constans.Database.DATABASE_NAME
import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import br.com.hellodev.moviestreaming.data.local.db.AppDatabase
import org.koin.dsl.module

val localModule = module {
    single<AppPreferences> {
        AppPreferences(context = get())
    }

    single {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        )
    }

    single {
        get<AppDatabase>().favoriteDao()
    }

}