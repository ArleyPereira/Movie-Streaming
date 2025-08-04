package br.com.hellodev.moviestreaming.presenter.features.main.search.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                    .windowInsetsPadding(WindowInsets.statusBars),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextFieldUI(
                    modifier = Modifier
                        .weight(1f)
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

                Card(
                    onClick = {},
                    modifier = Modifier
                        .padding(end = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MovieStreamingTheme.colorScheme.alphaDefaultColor
                    ),
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(16.dp),
                            tint = Color.Unspecified
                        )
                    }
                )
            }
        },
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor
    ) { mPaddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = mPaddingValues.calculateTopPadding(),
                end = 16.dp,
                bottom = paddingValues.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.movies) { movie ->
                ImageUI(
                    modifier = Modifier
                        .height(200.dp),
                    imageModel = movie.posterPath,
                    contentScale = ContentScale.Crop,
                    previewPlaceholder = painterResource(R.drawable.movie_placeholder),
                    onClick = { }
                )
            }
        }
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