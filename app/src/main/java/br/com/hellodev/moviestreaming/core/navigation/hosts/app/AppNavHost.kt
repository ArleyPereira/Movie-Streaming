package br.com.hellodev.moviestreaming.core.navigation.hosts.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.core.navigation.routes.app.AppRoutes
import br.com.hellodev.moviestreaming.presenter.screens.app.AppScreen

fun NavGraphBuilder.appNavHost(
    navigateToHomeAuthentication: () -> Unit
) {
    navigation<AppRoutes.Graph>(
        startDestination = AppRoutes.App
    ) {
        composable<AppRoutes.App> {
            AppScreen(
                navigateToHomeAuthentication = navigateToHomeAuthentication
            )
        }
    }
}