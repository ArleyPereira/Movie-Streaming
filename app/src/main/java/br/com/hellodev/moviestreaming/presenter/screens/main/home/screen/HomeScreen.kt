package br.com.hellodev.moviestreaming.presenter.screens.main.home.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.moviestreaming.presenter.screens.main.home.action.HomeAction
import br.com.hellodev.moviestreaming.presenter.screens.main.home.state.HomeState
import br.com.hellodev.moviestreaming.presenter.screens.main.home.viewmodel.HomeViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    HomeContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun HomeContent(
    state: HomeState,
    action: (HomeAction) -> Unit
) {

}

@Preview
@Composable
private fun HomePreview() {
    MovieStreamingTheme {
        HomeContent(
            state = HomeState(),
            action = {}
        )
    }
}