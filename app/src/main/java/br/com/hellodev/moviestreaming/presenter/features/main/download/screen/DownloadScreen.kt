package br.com.hellodev.moviestreaming.presenter.features.main.download.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.moviestreaming.presenter.features.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.features.main.download.state.DownloadState
import br.com.hellodev.moviestreaming.presenter.features.main.download.viewModel.DownloadViewModel
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