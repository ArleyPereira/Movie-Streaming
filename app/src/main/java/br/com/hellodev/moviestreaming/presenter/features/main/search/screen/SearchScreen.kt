package br.com.hellodev.moviestreaming.presenter.features.main.search.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import br.com.hellodev.moviestreaming.presenter.features.main.search.action.SearchAction
import br.com.hellodev.moviestreaming.presenter.features.main.search.state.SearchState
import br.com.hellodev.moviestreaming.presenter.features.main.search.viewmodel.SearchViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    paddingValues: PaddingValues = PaddingValues()
) {
    val viewModel = koinViewModel<SearchViewModel>()
    val state by viewModel.state.collectAsState()

    SearchContent(
        paddingValues = paddingValues,
        state = state,
        action = viewModel::submitAction
    )
}

@Composable
private fun SearchContent(
    paddingValues: PaddingValues = PaddingValues(),
    state: SearchState,
    action: (SearchAction) -> Unit
) {

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