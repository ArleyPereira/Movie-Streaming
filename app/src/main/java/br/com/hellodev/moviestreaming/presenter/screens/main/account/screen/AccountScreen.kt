package br.com.hellodev.moviestreaming.presenter.screens.main.account.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.menu.MenuType
import br.com.hellodev.moviestreaming.core.enums.menu.MenuType.*
import br.com.hellodev.moviestreaming.presenter.components.header.HeaderScreen
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItemDarkModeUI
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItemLanguageUI
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItemUI
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItems
import br.com.hellodev.moviestreaming.presenter.screens.main.account.action.AccountAction
import br.com.hellodev.moviestreaming.presenter.screens.main.account.state.AccountState
import br.com.hellodev.moviestreaming.presenter.screens.main.account.viewmodel.AccountViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen() {
    val viewModel = koinViewModel<AccountViewModel>()
    val state by viewModel.state.collectAsState()

    AccountContent(
        state = state,
        action = viewModel::submitAction,
        onItemClick = { menu ->
            when(menu.type){
                EDIT_PROFILE -> {}
                NOTIFICATION -> {}
                DOWNLOAD -> {}
                SECURITY -> {}
                LANGUAGE -> {}
                DARK_MODE -> {}
                HELP_CENTER -> {}
                PRIVACY_POLICY -> {}
                LOGOUT -> {}
            }
        }
    )
}

@Composable
private fun AccountContent(
    state: AccountState,
    action: (AccountAction) -> Unit,
    onItemClick: (MenuItems) -> Unit
) {
    Scaffold(
        topBar = {
            HeaderScreen(
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        start = 24.dp,
                        end = 24.dp
                    ),
                title = R.string.label_account_bottom_app_bar
            )
        },
        containerColor = MovieStreamingTheme.colorScheme.backgroundColor,
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(MenuItems.items()) { item ->
                    when(item.type){
                        LANGUAGE -> {
                            MenuItemLanguageUI(
                                icon = item.icon,
                                label = item.label,
                                onClick = { onItemClick(item) }
                            )
                        }
                        DARK_MODE -> {
                            MenuItemDarkModeUI(
                                icon = item.icon,
                                label = item.label,
                                isDarkMode = false,
                                onCheckedChange = { onItemClick(item) }
                            )
                        }
                        else -> {
                            MenuItemUI(
                                icon = item.icon,
                                label = item.label,
                                onClick = { onItemClick(item) }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun AccountPreview() {
    MovieStreamingTheme {
        AccountContent(
            state = AccountState(),
            action = {},
            onItemClick = {}
        )
    }
}