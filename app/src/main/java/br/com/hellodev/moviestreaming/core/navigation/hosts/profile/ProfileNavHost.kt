package br.com.hellodev.moviestreaming.core.navigation.hosts.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.core.navigation.routes.profile.ProfileRoutes
import br.com.hellodev.moviestreaming.presenter.features.profile.screen.EditProfileScreen

fun NavGraphBuilder.profileNavHost(
    navHostController: NavHostController
) {
    navigation<ProfileRoutes.Graph>(
        startDestination = ProfileRoutes.EditProfile
    ) {
        composable<ProfileRoutes.EditProfile> {
            EditProfileScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}