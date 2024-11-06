package br.com.hellodev.moviestreaming.core.navigation.hosts.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.moviestreaming.core.navigation.hosts.app.appNavHost
import br.com.hellodev.moviestreaming.core.navigation.hosts.authentication.authenticationNavHost
import br.com.hellodev.moviestreaming.core.navigation.routes.app.AppRoutes
import br.com.hellodev.moviestreaming.core.navigation.routes.authentication.AuthenticationRoutes
import br.com.hellodev.moviestreaming.core.navigation.routes.onboarding.OnboardingRoutes
import br.com.hellodev.moviestreaming.presenter.features.splash.screen.SplashScreen
import br.com.hellodev.moviestreaming.presenter.features.welcome.screen.WelcomeScreen

@Composable
fun OnboardingNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = OnboardingRoutes.Splash
    ) {
        composable<OnboardingRoutes.Splash> {
            SplashScreen(
                navigateToAppScreen = {
                    navHostController.navigate(AppRoutes.Graph) {
                        popUpTo(OnboardingRoutes.Splash) {
                            inclusive = true
                        }
                    }
                },
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

        appNavHost(
            navigateToHomeAuthentication = {
                navHostController.navigate(AuthenticationRoutes.Graph) {
                    popUpTo(0) {
                        inclusive = true
                    }
                }
            }
        )
    }
}