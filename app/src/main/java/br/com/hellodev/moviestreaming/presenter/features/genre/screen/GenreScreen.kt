package br.com.hellodev.moviestreaming.presenter.features.genre.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.domain.remote.model.genre.Genre
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.moviestreaming.presenter.components.radio.RadioButtonUi
import br.com.hellodev.moviestreaming.presenter.components.topAppBar.TopAppBarUI
import br.com.hellodev.moviestreaming.presenter.features.genre.action.GenreAction
import br.com.hellodev.moviestreaming.presenter.features.genre.state.GenreState
import br.com.hellodev.moviestreaming.presenter.features.genre.viewmodel.GenreViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun GenreScreen(
    onGenreSelected: (Genre?) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<GenreViewModel>()
    val state by viewModel.state.collectAsState()

    GenreContent(
        state = state,
        action = viewModel::submitAction,
        onGenreSelected = onGenreSelected,
        onBackPressed = onBackPressed
    )
}

@Composable
fun GenreContent(
    state: GenreState,
    action: (GenreAction) -> Unit,
    onGenreSelected: (Genre?) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = "Gêneros",
                onClick = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
            ) {
                HorizontalDividerUI()

                PrimaryButton(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 32.dp
                        ),
                    text = "Selecionar",
                    enabled = state.selectedGenre != null,
                    onClick = { onGenreSelected(state.selectedGenre) }
                )
            }
        },
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    bottom = 24.dp,
                    top = 8.dp
                )
            ) {
                items(state.genres) { genre ->
                    RadioButtonUi(
                        selected = genre == state.selectedGenre,
                        text = genre.name ?: "",
                        onClick = {
                            action(GenreAction.OnGenreSelected(genre))
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun GenrePreview() {
    MovieStreamingTheme {
        GenreContent(
            state = GenreState(
                genres = Genre.items
            ),
            action = {},
            onGenreSelected = {},
            onBackPressed = {}
        )
    }
}