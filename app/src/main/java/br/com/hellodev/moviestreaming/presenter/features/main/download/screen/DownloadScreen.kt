package br.com.hellodev.moviestreaming.presenter.features.main.download.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType
import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType.DELETE_BOTTOM_SHEET
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.presenter.components.bottom.sheet.delete.BottomSheetDelete
import br.com.hellodev.moviestreaming.presenter.components.bottom.sheet.drag.DragBottomSheet
import br.com.hellodev.moviestreaming.presenter.components.download.DownloadItemUI
import br.com.hellodev.moviestreaming.presenter.features.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.features.main.download.state.DownloadState
import br.com.hellodev.moviestreaming.presenter.features.main.download.viewModel.DownloadViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import kotlinx.coroutines.launch
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun DownloadContent(
    paddingValues: PaddingValues = PaddingValues(),
    state: DownloadState,
    action: (DownloadAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

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
                    items(
                        items = state.movies,
                        key = { movie -> movie.id ?: 0 }
                    ) { movie ->
                        DownloadItemUI(
                            modifier = Modifier
                                .animateItem(),
                            movie = movie,
                            onDeleteClick = {
                                action(DownloadAction.OnSelectedMovie(movie))
                            }
                        )
                    }
                }
            }

            when (state.sheetType) {
                DELETE_BOTTOM_SHEET -> {
                    state.selectedMovie?.let { selectedMovie ->
                        ModalBottomSheet(
                            onDismissRequest = {
                                action(DownloadAction.SetCurrentBottomSheet(SheetType.EMPTY_BOTTOM_SHEET))
                            },
                            sheetState = sheetState,
                            containerColor = MovieStreamingTheme.colorScheme.secondaryBackgroundColor,
                            dragHandle = { DragBottomSheet() },
                            content = {
                                BottomSheetDelete(
                                    movie = selectedMovie,
                                    onCancelClick = {
                                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                action(
                                                    DownloadAction.SetCurrentBottomSheet(
                                                        SheetType.EMPTY_BOTTOM_SHEET
                                                    )
                                                )
                                            }
                                        }
                                    },
                                    onConfirmClick = {
                                        action(DownloadAction.OnDeleteMovie)
                                    }
                                )
                            }
                        )
                    }
                }

                else -> {}
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