package br.com.hellodev.moviestreaming.presenter.screens.main.account.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
        action = viewModel::submitAction
    )
}

@Composable
private fun AccountContent(
    state: AccountState,
    action: (AccountAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieStreamingTheme.colorScheme.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Account")
    }
}

@Preview
@Composable
private fun AccountPreview() {
    MovieStreamingTheme {
        AccountContent(
            state = AccountState(),
            action = {}
        )
    }
}