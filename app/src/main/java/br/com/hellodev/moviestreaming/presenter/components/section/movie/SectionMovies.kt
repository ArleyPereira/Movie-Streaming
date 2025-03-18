package br.com.hellodev.moviestreaming.presenter.components.section.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun SectionMovies(
    modifier: Modifier = Modifier,
    leftText: String,
    rightText: String,
    movies: List<Movie>,
    onMovieClick: () -> Unit,
    onRightClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = leftText,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(700),
                color = MovieStreamingTheme.colorScheme.textColor
            )
        )

        Text(
            text = rightText,
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = onRightClick
                )
                .padding(vertical = 24.dp),
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 19.6.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(600),
                color = MovieStreamingTheme.colorScheme.defaultColor,
                letterSpacing = 0.2.sp,
            )
        )
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) {
            ImageUI(
                modifier = Modifier
                    .width(150.dp)
                    .height(200.dp),
                imageModel = null,
                contentScale = ContentScale.Crop,
                previewPlaceholder = painterResource(R.drawable.movie_placeholder),
                onClick = onMovieClick
            )
        }
    }
}

@Preview
@Composable
private fun SectionMoviesPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
        ) {
            SectionMovies(
                leftText = "Em cartaz",
                rightText = "Ver tudo",
                movies = listOf(Movie(), Movie(), Movie(), Movie(), Movie(), Movie()),
                onMovieClick = {},
                onRightClick = {}
            )

            SectionMovies(
                leftText = "Em cartaz",
                rightText = "Ver tudo",
                movies = listOf(Movie(), Movie(), Movie(), Movie(), Movie(), Movie()),
                onMovieClick = {},
                onRightClick = {}
            )

            SectionMovies(
                leftText = "Em cartaz",
                rightText = "Ver tudo",
                movies = listOf(Movie(), Movie(), Movie(), Movie(), Movie(), Movie()),
                onMovieClick = {},
                onRightClick = {}
            )
        }
    }
}