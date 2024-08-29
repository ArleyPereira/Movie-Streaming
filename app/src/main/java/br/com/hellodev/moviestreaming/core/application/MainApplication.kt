package br.com.hellodev.moviestreaming.core.application

import android.app.Application
import br.com.hellodev.moviestreaming.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(repositoryModule)
        }
    }
}