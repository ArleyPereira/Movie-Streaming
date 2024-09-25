package br.com.hellodev.moviestreaming.presenter.screens.main.search.screen

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
import br.com.hellodev.moviestreaming.presenter.screens.main.search.action.SearchAction
import br.com.hellodev.moviestreaming.presenter.screens.main.search.state.SearchState
import br.com.hellodev.moviestreaming.presenter.screens.main.search.viewmodel.SearchViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen() {
    val viewModel = koinViewModel<SearchViewModel>()
    val state by viewModel.state.collectAsState()

    SearchContent(
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun SearchContent(
    state: SearchState,
    action: (SearchAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MovieStreamingTheme.colorScheme.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Search")
    }
}

@Preview
@Composable
private fun SearchPreview() {
    MovieStreamingTheme {
        SearchContent(
            state = SearchState(),
            action = {}
        )
    }
}