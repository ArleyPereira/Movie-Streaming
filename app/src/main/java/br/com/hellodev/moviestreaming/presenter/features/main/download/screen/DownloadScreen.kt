package br.com.hellodev.moviestreaming.presenter.features.main.download.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType
import br.com.hellodev.moviestreaming.core.enums.sheet.SheetType.DELETE_BOTTOM_SHEET
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.presenter.components.bottom.sheet.delete.BottomSheetDelete
import br.com.hellodev.moviestreaming.presenter.components.bottom.sheet.drag.DragBottomSheet
import br.com.hellodev.moviestreaming.presenter.components.download.DownloadItemUI
import br.com.hellodev.moviestreaming.presenter.components.loading.LoadingScreenUI
import br.com.hellodev.moviestreaming.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.moviestreaming.presenter.features.main.download.action.DownloadAction
import br.com.hellodev.moviestreaming.presenter.features.main.download.state.DownloadState
import br.com.hellodev.moviestreaming.presenter.features.main.download.viewModel.DownloadViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun DownloadScreen(
    paddingValues: PaddingValues = PaddingValues()
) {
    val viewModel = koinViewModel<DownloadViewModel>()
    val state by viewModel.state.collectAsState()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.submitAction(DownloadAction.InitData)
    }

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
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TextFieldUI(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                    .windowInsetsPadding(WindowInsets.statusBars)
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
                        action(DownloadAction.OnSearch)
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                ),
                onValueChange = {
                    action(DownloadAction.OnQueryChanged(it))
                }
            )
        },
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
        content = { mPaddingValues ->
            when {
                state.isLoading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoadingScreenUI()
                    }
                }

                state.moviesFiltered.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.not_found_placeholder),
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            text = stringResource(R.string.label_title_empty_state_search_screen),
                            style = TextStyle(
                                fontSize = 18.sp,
                                lineHeight = 28.8.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(700),
                                color = MovieStreamingTheme.colorScheme.defaultColor,
                                textAlign = TextAlign.Center
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = stringResource(R.string.label_message_empty_state_search_screen),
                            style = TextStyle(
                                lineHeight = 25.2.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(500),
                                color = MovieStreamingTheme.colorScheme.whiteColor,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = mPaddingValues.calculateTopPadding(),
                            end = 16.dp,
                            bottom = paddingValues.calculateBottomPadding()
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(
                            items = state.moviesFiltered,
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