package br.com.hellodev.moviestreaming.core.navigation.routes.hosts.onboarding

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.moviestreaming.core.navigation.routes.onboarding.OnboardingRoutes
import br.com.hellodev.moviestreaming.presenter.screens.splash.SplashScreen
import br.com.hellodev.moviestreaming.presenter.screens.welcome.WelcomeScreen

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
                }
            )
        }

        composable<OnboardingRoutes.Welcome> {
            WelcomeScreen()
        }
    }
}