package br.com.hellodev.moviestreaming.presenter.features.main.search.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.textfield.default.TextFieldUI
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
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.statusBars),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextFieldUI(
                    modifier = Modifier
                        .padding(16.dp),
                    value = state.query,
                    placeholder = "Pesquisar",
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_line),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            action(SearchAction.OnSearch)
                        }
                    ),
                    onValueChange = {
                        action(SearchAction.OnQueryChanged(it))
                    }
                )
            }
        },
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor
    ) { paddingValues ->

    }
}

@PreviewLightDark
@Composable
private fun SearchPreview() {
    MovieStreamingTheme {
        SearchContent(
            state = SearchState(),
            action = {}
        )
    }
}