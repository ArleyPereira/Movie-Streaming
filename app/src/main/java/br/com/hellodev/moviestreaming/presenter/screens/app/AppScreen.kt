package br.com.hellodev.moviestreaming.presenter.screens.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun AppScreen() {
    AppContent()
}

@Composable
private fun AppContent() {

}

@Preview
@Composable
private fun AppPreview() {
    MovieStreamingTheme {
        AppContent()
    }
}