package br.com.hellodev.moviestreaming.presenter.features.main.download.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.presenter.components.download.DownloadItemUI
import br.com.hellodev.moviestreaming.presenter.features.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.features.main.download.state.DownloadState
import br.com.hellodev.moviestreaming.presenter.features.main.download.viewModel.DownloadViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DownloadScreen(
    paddingValues: PaddingValues = PaddingValues()
) {
    val viewModel = koinViewModel<DownloadViewModel>()
    val state by viewModel.state.collectAsState()

    DownloadContent(
        paddingValues = paddingValues,
        state = state,
        action = viewModel::submitAction
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun DownloadContent(
    paddingValues: PaddingValues = PaddingValues(),
    state: DownloadState,
    action: (DownloadAction) -> Unit
) {
    Scaffold(
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        end = 16.dp,
                        top = paddingValues.calculateTopPadding() + 16.dp,
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(state.movies) { movie ->
                        DownloadItemUI(
                            movie = movie,
                            onDeleteClick = {}
                        )
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun DownloadPreview() {
    MovieStreamingTheme {
        DownloadContent(
            state = DownloadState(
                movies = listOf(
                    Movie(
                        id = 1,
                        title = "Movie 1",
                        overview = "Overview 1",
                        runtime = 120,
                    ),
                    Movie(
                        id = 2,
                        title = "Movie 2",
                        overview = "Overview 2",
                        runtime = 120,
                    )
                )
            ),
            action = {}
        )
    }
}