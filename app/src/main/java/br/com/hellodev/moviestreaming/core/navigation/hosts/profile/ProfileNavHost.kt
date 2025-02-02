package br.com.hellodev.moviestreaming.core.navigation.hosts.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import br.com.hellodev.moviestreaming.core.constans.NavigationKeys.EDIT_PROFILE_SCREEN_KEY
import br.com.hellodev.moviestreaming.core.navigation.extensions.getObject
import br.com.hellodev.moviestreaming.core.navigation.extensions.putObject
import br.com.hellodev.moviestreaming.core.navigation.routes.profile.ProfileRoutes
import br.com.hellodev.moviestreaming.presenter.features.country.screen.CountryScreen
import br.com.hellodev.moviestreaming.presenter.features.genre.screen.GenreScreen
import br.com.hellodev.moviestreaming.presenter.features.profile.parameter.EditProfileParameter
import br.com.hellodev.moviestreaming.presenter.features.profile.screen.EditProfileScreen

fun NavGraphBuilder.profileNavHost(
    navHostController: NavHostController
) {
    navigation<ProfileRoutes.Graph>(
        startDestination = ProfileRoutes.EditProfile
    ) {
        composable<ProfileRoutes.EditProfile> { backStackEntry ->
            val parameter = backStackEntry.savedStateHandle.getObject<EditProfileParameter>(
                key = EDIT_PROFILE_SCREEN_KEY
            )

            EditProfileScreen(
                parameter = parameter,
                navigateToGenreScreen = {
                    navHostController.navigate(ProfileRoutes.Genre)
                },
                navigateToCountryScreen = {
                    navHostController.navigate(ProfileRoutes.Country)
                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<ProfileRoutes.Genre> {
            GenreScreen(
                onGenreSelected = { genre ->
                    val parameter = EditProfileParameter(genre = genre)
                    navHostController.previousBackStackEntry?.savedStateHandle?.putObject(
                        key = EDIT_PROFILE_SCREEN_KEY,
                        value = parameter
                    )
                    navHostController.popBackStack()
                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<ProfileRoutes.Country> {
            CountryScreen(
                onCountrySelected = { country ->
                    val parameter = EditProfileParameter(country = country)
                    navHostController.previousBackStackEntry?.savedStateHandle?.putObject(
                        key = EDIT_PROFILE_SCREEN_KEY,
                        value = parameter
                    )
                    navHostController.popBackStack()
                },
                onBackPressed = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}