package br.com.hellodev.moviestreaming.presenter.features.main.details.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.moviestreaming.presenter.features.main.details.viewmodel.MovieDetailsViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<MovieDetailsViewModel>()
    val state by viewModel.state.collectAsState()

    MovieDetailsContent()
}

@Composable
private fun MovieDetailsContent() {

}

@Preview
@Composable
private fun MovieDetailsPreview() {
    MovieStreamingTheme {
        MovieDetailsContent()
    }
}