package br.com.hellodev.moviestreaming.core.navigation.hosts.bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.hellodev.moviestreaming.core.navigation.routes.bar.BottomAppBarRoutes
import br.com.hellodev.moviestreaming.presenter.screens.main.account.screen.AccountScreen
import br.com.hellodev.moviestreaming.presenter.screens.main.download.screen.DownloadScreen
import br.com.hellodev.moviestreaming.presenter.screens.main.favorite.screen.FavoriteScreen
import br.com.hellodev.moviestreaming.presenter.screens.main.home.screen.HomeScreen
import br.com.hellodev.moviestreaming.presenter.screens.main.search.screen.SearchScreen

@Composable
fun BottomAppBarNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = BottomAppBarRoutes.Home
    ) {
        composable<BottomAppBarRoutes.Home> {
            HomeScreen()
        }

        composable<BottomAppBarRoutes.Search> {
            SearchScreen()
        }

        composable<BottomAppBarRoutes.Favorite> {
            FavoriteScreen()
        }

        composable<BottomAppBarRoutes.Download> {
            DownloadScreen()
        }

        composable<BottomAppBarRoutes.Account> {
            AccountScreen()
        }
    }
}