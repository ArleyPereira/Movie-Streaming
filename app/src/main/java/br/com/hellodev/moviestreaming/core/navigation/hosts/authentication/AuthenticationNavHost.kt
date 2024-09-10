package br.com.hellodev.moviestreaming.core.navigation.hosts.authentication

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.core.navigation.routes.authentication.AuthenticationRoutes
import br.com.hellodev.moviestreaming.presenter.screens.authentication.home.HomeAuthenticationScreen
import br.com.hellodev.moviestreaming.presenter.screens.authentication.login.screen.LoginScreen
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.screen.SignupScreen

fun NavGraphBuilder.authenticationNavHost(navHostController: NavHostController) {
    navigation<AuthenticationRoutes.Graph>(
        startDestination = AuthenticationRoutes.Home
    ) {
        composable<AuthenticationRoutes.Home> {
            HomeAuthenticationScreen(
                navigateToLoginScreen = {
                    navHostController.navigate(AuthenticationRoutes.Login)
                },
                navigateToSignupScreen = {
                    navHostController.navigate(AuthenticationRoutes.Signup)
                }
            )
        }

        composable<AuthenticationRoutes.Login> {
            LoginScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<AuthenticationRoutes.Signup> {
            SignupScreen(
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}