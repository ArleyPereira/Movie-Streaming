package br.com.hellodev.moviestreaming.core.navigation.hosts.authentication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.core.navigation.hosts.onboarding.AuthenticationRoutes
import br.com.hellodev.moviestreaming.presenter.screens.authentication.home.HomeAuthenticationScreen
import br.com.hellodev.moviestreaming.presenter.screens.authentication.login.screen.LoginScreen

fun NavGraphBuilder.authenticationNavHost(navHostController: NavHostController) {
    navigation<AuthenticationRoutes.Graph>(
        startDestination = AuthenticationRoutes.HomeAuthentication
    ) {
        composable<AuthenticationRoutes.HomeAuthentication> {
            HomeAuthenticationScreen()
        }

        composable<AuthenticationRoutes.Login> {
            LoginScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}