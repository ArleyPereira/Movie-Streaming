package br.com.hellodev.moviestreaming.core.navigation.routes.app

import kotlinx.serialization.Serializable

sealed class AppRoutes {

    @Serializable
    data object Graph : AppRoutes()

    @Serializable
    data object App : AppRoutes()

}