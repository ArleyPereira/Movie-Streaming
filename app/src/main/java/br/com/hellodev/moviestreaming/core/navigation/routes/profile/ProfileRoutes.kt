package br.com.hellodev.moviestreaming.core.navigation.routes.profile

import kotlinx.serialization.Serializable

sealed class ProfileRoutes {

    @Serializable
    data object Graph : ProfileRoutes()

    @Serializable
    data object EditProfile : ProfileRoutes()

    @Serializable
    data object Genre : ProfileRoutes()

    @Serializable
    data object Country : ProfileRoutes()

}