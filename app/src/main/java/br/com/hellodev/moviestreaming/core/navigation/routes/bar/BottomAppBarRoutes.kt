package br.com.hellodev.moviestreaming.core.navigation.routes.bar

import kotlinx.serialization.Serializable

sealed class BottomAppBarRoutes {

    @Serializable
    data object Home : BottomAppBarRoutes()

    @Serializable
    data object Search : BottomAppBarRoutes()

    @Serializable
    data object Favorite : BottomAppBarRoutes()

    @Serializable
    data object Download : BottomAppBarRoutes()

    @Serializable
    data object Account : BottomAppBarRoutes()

}