package br.com.hellodev.moviestreaming.presenter.features.main.details.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.navigation.tabs.MovieDetailsTabsItems
import br.com.hellodev.moviestreaming.presenter.components.button.OutlinedButton
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.cast.CastMovieUI
import br.com.hellodev.moviestreaming.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.components.review.MovieReviewUI
import br.com.hellodev.moviestreaming.presenter.components.topAppBar.TopAppBarUI
import br.com.hellodev.moviestreaming.presenter.features.main.details.state.MovieDetailsState
import br.com.hellodev.moviestreaming.presenter.features.main.details.viewmodel.MovieDetailsViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieDetailsContent(
    state: MovieDetailsState,
    onBackPressed: () -> Unit
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

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
                    .verticalScroll(rememberScrollState())
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                    .padding(
                        bottom = paddingValues.calculateBottomPadding()
                    )
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = state.movie?.title ?: "",
                        modifier = Modifier
                            .weight(1f),
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 28.8.sp,
                            fontFamily = UrbanistFamily,
                            fontWeight = FontWeight(700),
                            color = MovieStreamingTheme.colorScheme.textColor
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_heart_line),
                        contentDescription = null,
                        tint = MovieStreamingTheme.colorScheme.iconColor
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        tint = MovieStreamingTheme.colorScheme.iconColor
                    )
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_star_vote),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )

                            Text(
                                text = (state.movie?.voteAverage).toString().substring(0, 3),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontFamily = UrbanistFamily,
                                    fontWeight = FontWeight(500),
                                    color = MovieStreamingTheme.colorScheme.defaultColor,
                                    letterSpacing = 0.2.sp
                                )
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_right),
                                contentDescription = null,
                                tint = MovieStreamingTheme.colorScheme.defaultColor
                            )
                        }
                    }

                    item {
                        Text(
                            text = state.movie?.releaseDate ?: "",
                            style = TextStyle(
                                lineHeight = 19.6.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(600),
                                color = MovieStreamingTheme.colorScheme.textColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    item {
                        Text(
                            text = "13+",
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MovieStreamingTheme.colorScheme.defaultColor,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(
                                    vertical = 6.dp,
                                    horizontal = 10.dp
                                ),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(600),
                                color = MovieStreamingTheme.colorScheme.defaultColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    item {
                        Text(
                            text = "United States",
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MovieStreamingTheme.colorScheme.defaultColor,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(
                                    vertical = 6.dp,
                                    horizontal = 10.dp
                                ),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(600),
                                color = MovieStreamingTheme.colorScheme.defaultColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }

                    item {
                        Text(
                            text = "Subtitle",
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MovieStreamingTheme.colorScheme.defaultColor,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(
                                    vertical = 6.dp,
                                    horizontal = 10.dp
                                ),
                            style = TextStyle(
                                fontSize = 10.sp,
                                fontFamily = UrbanistFamily,
                                fontWeight = FontWeight(600),
                                color = MovieStreamingTheme.colorScheme.defaultColor,
                                letterSpacing = 0.2.sp
                            )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PrimaryButton(
                        text = stringResource(R.string.label_play_movie_details_screen),
                        modifier = Modifier
                            .height(38.dp)
                            .weight(1f),
                        icon = painterResource(id = R.drawable.ic_play),
                        onClick = {}
                    )

                    OutlinedButton(
                        text = stringResource(R.string.label_download_movie_details_screen),
                        modifier = Modifier
                            .height(38.dp)
                            .weight(1f),
                        icon = painterResource(id = R.drawable.ic_download_fill),
                        onClick = {}
                    )
                }

                Text(
                    text = stringResource(
                        R.string.label_genres_movie_details_screen,
                        state.movie?.genres?.joinToString(separator = ", ") { it?.name ?: "" }
                            .toString()
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(500),
                        color = MovieStreamingTheme.colorScheme.textColor,
                        letterSpacing = 0.2.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.movie?.overview ?: "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(500),
                        color = MovieStreamingTheme.colorScheme.textColor,
                        letterSpacing = 0.2.sp
                    ),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.credits?.cast ?: emptyList()) { cast ->
                        CastMovieUI(cast = cast)
                    }
                }

                PrimaryTabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = Color.Transparent,
                    indicator = {
                        Spacer(
                            modifier = Modifier
                                .tabIndicatorOffset(selectedTabIndex)
                                .height(4.dp)
                                .padding(horizontal = 16.dp)
                                .background(
                                    color = MovieStreamingTheme.colorScheme.defaultColor,
                                    shape = RoundedCornerShape(size = 100.dp)
                                )
                        )
                    },
                    divider = {
                        HorizontalDividerUI(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        )
                    }
                ) {
                    MovieDetailsTabsItems.items.forEachIndexed { index, tab ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(
                                    text = stringResource(tab.title),
                                    style = TextStyle(
                                        lineHeight = 22.4.sp,
                                        fontFamily = UrbanistFamily,
                                        fontWeight = FontWeight(600),
                                        color = if (selectedTabIndex == index) {
                                            MovieStreamingTheme.colorScheme.defaultColor
                                        } else {
                                            MovieStreamingTheme.colorScheme.tabRowUnselectedTextColor
                                        },
                                        textAlign = TextAlign.Center,
                                        letterSpacing = 0.2.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        )
                    }
                }

                when (selectedTabIndex) {
                    0 -> {

                    }

                    1 -> {

                    }

                    2 -> {
                        state.reviews?.forEach { review ->
                            MovieReviewUI(
                                modifier = Modifier
                                    .padding(16.dp),
                                review = review
                            )
                        }
                    }
                }
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