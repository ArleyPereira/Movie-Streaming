package br.com.hellodev.moviestreaming.presenter.features.main.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.moviestreaming.presenter.components.section.movie.SectionMovies
import br.com.hellodev.moviestreaming.presenter.features.main.home.action.HomeAction
import br.com.hellodev.moviestreaming.presenter.features.main.home.state.HomeState
import br.com.hellodev.moviestreaming.presenter.features.main.home.viewmodel.HomeViewModel
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
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SectionMovies(
                    leftText = "Lançamentos",
                    rightText = "Ver tudo",
                    movies = state.nowPlayingList,
                    onMovieClick = {},
                    onRightClick = {}
                )

                SectionMovies(
                    leftText = "Populares",
                    rightText = "Ver tudo",
                    movies = state.popularList,
                    onMovieClick = {},
                    onRightClick = {}
                )
            }
        }
    )
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