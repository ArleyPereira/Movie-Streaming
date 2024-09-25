package br.com.hellodev.moviestreaming.presenter.screens.app

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.moviestreaming.core.navigation.bar.BottomAppBarItems
import br.com.hellodev.moviestreaming.core.navigation.hosts.bar.BottomAppBarNavHost
import br.com.hellodev.moviestreaming.presenter.components.bar.BottomAppBarUI
import br.com.hellodev.moviestreaming.presenter.components.bar.BottomNavItemUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun AppScreen() {
    AppContent()
}

@Composable
private fun AppContent() {
    val navHostController = rememberNavController()
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    Scaffold(
        bottomBar = {
            BottomAppBarUI(
                modifier = Modifier,
                currentDestination = currentDestination,
                onItemClick = {
                    navHostController.navigate(it.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        },
        containerColor = MovieStreamingTheme.colorScheme.backgroundColor,
        content = { paddingValues ->
            BottomAppBarNavHost(
                modifier = Modifier
                    .padding(paddingValues),
                navHostController = navHostController
            )
        }
    )
}

@Preview
@Composable
private fun AppPreview() {
    MovieStreamingTheme {
        AppContent()
    }
}