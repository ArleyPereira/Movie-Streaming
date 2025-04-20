package br.com.hellodev.moviestreaming.presenter.features.main.details.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.components.topAppBar.TopAppBarUI
import br.com.hellodev.moviestreaming.presenter.features.main.details.state.MovieDetailsState
import br.com.hellodev.moviestreaming.presenter.features.main.details.viewmodel.MovieDetailsViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<MovieDetailsViewModel>()
    val state by viewModel.state.collectAsState()

    MovieDetailsContent(
        state = state,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun MovieDetailsContent(
    state: MovieDetailsState,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                containerColor = Color.Transparent,
                actions = {
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cast),
                                contentDescription = null,
                                tint = MovieStreamingTheme.colorScheme.iconColor
                            )
                        }
                    )
                },
                onBackPressed = onBackPressed,
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
            ) {
                ImageUI(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(320.dp),
                    imageModel = state.movie?.backdropPath,
                    contentScale = ContentScale.Crop,
                    previewPlaceholder = painterResource(id = R.drawable.placeholder_welcome),
                    shape = RoundedCornerShape(0.dp),
                    isLoading = false,
                    onClick = {}
                )
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun MovieDetailsPreview() {
    MovieStreamingTheme {
        MovieDetailsContent(
            state = MovieDetailsState(),
            onBackPressed = {}
        )
    }
}