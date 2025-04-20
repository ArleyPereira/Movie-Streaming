package br.com.hellodev.moviestreaming.presenter.features.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.moviestreaming.core.navigation.hosts.bar.BottomAppBarNavHost
import br.com.hellodev.moviestreaming.presenter.components.bottom.bar.BottomBarUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun AppScreen(
    navigateToHomeAuthentication: () -> Unit
) {
    AppContent(
        navigateToHomeAuthentication = navigateToHomeAuthentication
    )
}

@Composable
private fun AppContent(
    navigateToHomeAuthentication: () -> Unit
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomBarUI(
                currentDestination = currentDestination,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        },
        content = { paddingValues ->
            BottomAppBarNavHost(
                modifier = Modifier
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor),
                navHostController = navController,
                paddingValues = PaddingValues(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding() + 16.dp
                ),
                navigateToHomeAuthentication = navigateToHomeAuthentication
            )
        }
    )
}

@Preview
@Composable
private fun AppPreview() {
    MovieStreamingTheme {
        AppContent(
            navigateToHomeAuthentication = {}
        )
    }
}