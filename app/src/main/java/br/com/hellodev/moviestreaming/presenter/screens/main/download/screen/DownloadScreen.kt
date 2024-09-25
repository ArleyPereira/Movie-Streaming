package br.com.hellodev.moviestreaming.presenter.screens.main.download.screen

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
import br.com.hellodev.moviestreaming.presenter.screens.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.screens.main.download.state.DownloadState
import br.com.hellodev.moviestreaming.presenter.screens.main.download.viewModel.DownloadViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DownloadScreen() {
    val viewModel = koinViewModel<DownloadViewModel>()
    val state by viewModel.state.collectAsState()

    DownloadContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun DownloadContent(
    state: DownloadState,
    action: (DownloadAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieStreamingTheme.colorScheme.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Download")
    }
}

@Preview
@Composable
private fun DownloadPreview() {
    MovieStreamingTheme {
        DownloadContent(
            state = DownloadState(),
            action = {}
        )
    }
}