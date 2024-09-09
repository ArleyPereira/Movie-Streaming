package br.com.hellodev.moviestreaming.core.navigation.routes.authentication

import kotlinx.serialization.Serializable

sealed class AuthenticationRoutes {

    @Serializable
    data object Graph : AuthenticationRoutes()

    @Serializable
    data object Home : AuthenticationRoutes()

    @Serializable
    data object Login : AuthenticationRoutes()

    @Serializable
    data object Signup : AuthenticationRoutes()

}