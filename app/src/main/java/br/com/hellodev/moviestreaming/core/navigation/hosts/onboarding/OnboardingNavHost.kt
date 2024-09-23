package br.com.hellodev.moviestreaming.core.navigation.hosts.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.moviestreaming.core.navigation.hosts.app.appNavHost
import br.com.hellodev.moviestreaming.core.navigation.hosts.authentication.authenticationNavHost
import br.com.hellodev.moviestreaming.core.navigation.routes.authentication.AuthenticationRoutes
import br.com.hellodev.moviestreaming.core.navigation.routes.onboarding.OnboardingRoutes
import br.com.hellodev.moviestreaming.presenter.screens.splash.screen.SplashScreen
import br.com.hellodev.moviestreaming.presenter.screens.welcome.screen.WelcomeScreen

@Composable
fun OnboardingNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingRoutes.Splash
    ) {
        composable<OnboardingRoutes.Splash> {
            SplashScreen(
                navigateToWelcomeScreen = {
                    navHostController.navigate(OnboardingRoutes.Welcome) {
                        popUpTo(OnboardingRoutes.Splash) {
                            inclusive = true
                        }
                    }
                },
                navigateToHomeAuthenticationScreen = {
                    navHostController.navigate(AuthenticationRoutes.Graph) {
                        popUpTo(OnboardingRoutes.Splash) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<OnboardingRoutes.Welcome> {
            WelcomeScreen(
                navigateToHomeAuthenticationScreen = {
                    navHostController.navigate(AuthenticationRoutes.Graph) {
                        popUpTo(OnboardingRoutes.Welcome) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        authenticationNavHost(navHostController = navHostController)

        appNavHost(navHostController = navHostController)
    }
}