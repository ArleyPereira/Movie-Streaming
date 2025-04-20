package br.com.hellodev.moviestreaming.core.navigation.hosts.bar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.moviestreaming.core.navigation.hosts.profile.profileNavHost
import br.com.hellodev.moviestreaming.core.navigation.routes.bar.BottomAppBarRoutes
import br.com.hellodev.moviestreaming.core.navigation.routes.profile.ProfileRoutes
import br.com.hellodev.moviestreaming.presenter.features.main.account.screen.AccountScreen
import br.com.hellodev.moviestreaming.presenter.features.main.download.screen.DownloadScreen
import br.com.hellodev.moviestreaming.presenter.features.main.favorite.screen.FavoriteScreen
import br.com.hellodev.moviestreaming.presenter.features.main.home.screen.HomeScreen
import br.com.hellodev.moviestreaming.presenter.features.main.search.screen.SearchScreen

@Composable
fun BottomAppBarNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(),
    navigateToHomeAuthentication: () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomAppBarRoutes.Home,
        modifier = modifier
    ) {
        composable<BottomAppBarRoutes.Home> {
            HomeScreen(
                paddingValues = paddingValues
            )
        }

        composable<BottomAppBarRoutes.Search> {
            SearchScreen(
                paddingValues = paddingValues
            )
        }

        composable<BottomAppBarRoutes.Favorite> {
            FavoriteScreen(
                paddingValues = paddingValues
            )
        }

        composable<BottomAppBarRoutes.Download> {
            DownloadScreen(
                paddingValues = paddingValues
            )
        }

        composable<BottomAppBarRoutes.Account> {
            AccountScreen(
                paddingValues = paddingValues,
                navigateToHomeAuthentication = navigateToHomeAuthentication,
                navigateToEditProfileScreen = {
                    navHostController.navigate(ProfileRoutes.EditProfile)
                }
            )
        }

        profileNavHost(navHostController = navHostController)
    }
}