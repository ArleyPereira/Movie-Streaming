package br.com.hellodev.moviestreaming.presenter.features.main.home.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
fun HomeScreen(
    paddingValues: PaddingValues = PaddingValues(),
    navigateToMovieDetailsScreen: (Int) -> Unit
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()

    HomeContent(
        paddingValues = paddingValues,
        state = state,
        navigateToMovieDetailsScreen = navigateToMovieDetailsScreen,
        action = viewModel::submitAction
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun HomeContent(
    paddingValues: PaddingValues = PaddingValues(),
    state: HomeState,
    navigateToMovieDetailsScreen: (Int) -> Unit,
    action: (HomeAction) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                SectionMovies(
                    leftText = "Lançamentos",
                    rightText = "Ver tudo",
                    movies = state.nowPlayingList,
                    onMovieClick = navigateToMovieDetailsScreen,
                    onRightClick = {}
                )

                SectionMovies(
                    leftText = "Populares",
                    rightText = "Ver tudo",
                    movies = state.popularList,
                    onMovieClick = navigateToMovieDetailsScreen,
                    onRightClick = {}
                )

                SectionMovies(
                    leftText = "Mais votados",
                    rightText = "Ver tudo",
                    movies = state.topRatedList,
                    onMovieClick = navigateToMovieDetailsScreen,
                    onRightClick = {}
                )

                SectionMovies(
                    leftText = "Em breve",
                    rightText = "Ver tudo",
                    movies = state.upcomingList,
                    onMovieClick = navigateToMovieDetailsScreen,
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
            navigateToMovieDetailsScreen = {},
            action = {}
        )
    }
}