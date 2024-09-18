package br.com.hellodev.moviestreaming.presenter.screens.main.account.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.moviestreaming.presenter.screens.main.account.action.AccountAction
import br.com.hellodev.moviestreaming.presenter.screens.main.account.state.AccountState
import br.com.hellodev.moviestreaming.presenter.screens.main.account.viewmodel.AccountViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AccountAction() {
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