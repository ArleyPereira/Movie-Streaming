package br.com.hellodev.moviestreaming.presenter.screens.main.favorite.screen

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
import br.com.hellodev.moviestreaming.presenter.screens.main.favorite.action.FavoriteAction
import br.com.hellodev.moviestreaming.presenter.screens.main.favorite.state.FavoriteState
import br.com.hellodev.moviestreaming.presenter.screens.main.favorite.viewmodel.FavoriteViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen() {
    val viewModel = koinViewModel<FavoriteViewModel>()
    val state by viewModel.state.collectAsState()

    FavoriteContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun FavoriteContent(
    state: FavoriteState,
    action: (FavoriteAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieStreamingTheme.colorScheme.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Favorite")
    }
}

@Preview
@Composable
private fun FavoritePreview() {
    MovieStreamingTheme {
        FavoriteContent(
            state = FavoriteState(),
            action = {}
        )
    }
}