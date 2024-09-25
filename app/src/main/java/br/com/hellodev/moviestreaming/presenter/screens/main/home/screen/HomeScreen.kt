package br.com.hellodev.moviestreaming.presenter.screens.main.home.screen

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieStreamingTheme.colorScheme.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Home")
    }
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