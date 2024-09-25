package br.com.hellodev.moviestreaming.presenter.components.bar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.moviestreaming.core.navigation.bar.BottomAppBarItems
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun BottomAppBarUI(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onItemClick: (BottomAppBarItems) -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        actions = {
            BottomAppBarItems.items.forEach { item ->
                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == item.route::class.qualifiedName
                } == true

                BottomNavItemUI(
                    item = item,
                    isSelected = isSelected,
                    onClick = { onItemClick(item) }
                )
            }
        },
        containerColor = MovieStreamingTheme.colorScheme.backgroundColor,
    )
}

@Preview
@Composable
private fun BottomAppBarUIPreview() {
    val navHostController = rememberNavController()
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

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
}