package br.com.hellodev.moviestreaming.core.navigation.hosts.onboarding

import kotlinx.serialization.Serializable

sealed class AuthenticationRoutes {

    @Serializable
    data object Graph : AuthenticationRoutes()

    @Serializable
    data object HomeAuthentication : AuthenticationRoutes()

    @Serializable
    data object Login : AuthenticationRoutes()

}