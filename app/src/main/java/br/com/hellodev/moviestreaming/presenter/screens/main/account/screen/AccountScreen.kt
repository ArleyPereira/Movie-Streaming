package br.com.hellodev.moviestreaming.presenter.screens.main.account.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.menu.MenuType
import br.com.hellodev.moviestreaming.core.enums.menu.MenuType.*
import br.com.hellodev.moviestreaming.domain.remote.model.User
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.header.HeaderScreen
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItemDarkModeUI
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItemLanguageUI
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItemUI
import br.com.hellodev.moviestreaming.presenter.components.menu.MenuItems
import br.com.hellodev.moviestreaming.presenter.screens.main.account.action.AccountAction
import br.com.hellodev.moviestreaming.presenter.screens.main.account.state.AccountState
import br.com.hellodev.moviestreaming.presenter.screens.main.account.viewmodel.AccountViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountScreen() {
    val viewModel = koinViewModel<AccountViewModel>()
    val state by viewModel.state.collectAsState()

    AccountContent(
        state = state,
        action = viewModel::submitAction,
        onItemClick = { menu ->
            when (menu.type) {
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
                    .padding(
                        top = paddingValues.calculateTopPadding()
                    ),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ImageUI(
                        modifier = Modifier
                            .size(120.dp),
                        imageModel = state.user?.photo,
                        contentScale = ContentScale.Crop,
                        previewPlaceholder = painterResource(id = R.drawable.placeholder_welcome),
                        shape = CircleShape,
                        isLoading = state.isLoading,
                        onClick = {}
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (
                        state.user?.name?.isNotEmpty() == true &&
                        state.user.surname?.isNotEmpty() == true
                    ) {
                        Text(
                            text = "${state.user.name} ${state.user.surname}",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 24.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight.Bold,
                                color = MovieStreamingTheme.colorScheme.textColor,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Text(
                        text = state.user?.email ?: "",
                        style = TextStyle(
                            lineHeight = 19.6.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight.Medium,
                            color = MovieStreamingTheme.colorScheme.textColor,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.2.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(MenuItems.items()) { item ->
                    when (item.type) {
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
            state = AccountState(
                user = User(
                    name = "Andrew",
                    surname = "Ainsley",
                    email = "john.mclean@examplepetstore.com",
                )
            ),
            action = {},
            onItemClick = {}
        )
    }
}