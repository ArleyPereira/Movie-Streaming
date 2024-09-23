package br.com.hellodev.moviestreaming.core.navigation.bar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.navigation.routes.bar.BottomAppBarRoutes

sealed class BottomAppBarItems(
    val route: BottomAppBarRoutes,
    @StringRes val label: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int
) {

    data object Home : BottomAppBarItems(
        route = BottomAppBarRoutes.Home,
        label = R.string.label_home_bottom_app_bar,
        selectedIcon = R.drawable.ic_home_fill,
        unselectedIcon = R.drawable.ic_home_line
    )

    data object Search : BottomAppBarItems(
        route = BottomAppBarRoutes.Search,
        label = R.string.label_search_bottom_app_bar,
        selectedIcon = R.drawable.ic_search_fill,
        unselectedIcon = R.drawable.ic_search_line
    )

    data object Favorite : BottomAppBarItems(
        route = BottomAppBarRoutes.Favorite,
        label = R.string.label_favorite_bottom_app_bar,
        selectedIcon = R.drawable.ic_heart_fill,
        unselectedIcon = R.drawable.ic_heart_line
    )

    data object Download : BottomAppBarItems(
        route = BottomAppBarRoutes.Download,
        label = R.string.label_download_bottom_app_bar,
        selectedIcon = R.drawable.ic_download_fill,
        unselectedIcon = R.drawable.ic_download_line
    )

    data object Account : BottomAppBarItems(
        route = BottomAppBarRoutes.Account,
        label = R.string.label_account_bottom_app_bar,
        selectedIcon = R.drawable.ic_user_fill,
        unselectedIcon = R.drawable.ic_user_line
    )

    companion object {
        val items = listOf(Home, Search, Favorite, Download, Account)
    }

}