package br.com.hellodev.moviestreaming.presenter.components.cast

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.domain.remote.model.cast.Cast
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun CastMovieUI(
    modifier: Modifier = Modifier,
    cast: Cast
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ImageUI(
            modifier = Modifier
                .size(40.dp),
            imageModel = cast.profilePath,
            contentScale = ContentScale.Crop,
            previewPlaceholder = painterResource(id = R.drawable.movie_placeholder),
            shape = CircleShape,
            onClick = {}
        )

        Text(
            text = cast.name ?: "",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight(600),
                color = MovieStreamingTheme.colorScheme.textColor,
                letterSpacing = 0.2.sp
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun CastMovieUIPreview() {
    MovieStreamingTheme {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
                .padding(32.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            CastMovieUI(
                cast = Cast(
                    id = 1,
                    name = "James Cameron",
                    profilePath = ""
                )
            )

            CastMovieUI(
                cast = Cast(
                    id = 1,
                    name = "James Cameron",
                    profilePath = ""
                )
            )

            CastMovieUI(
                cast = Cast(
                    id = 1,
                    name = "James Cameron",
                    profilePath = ""
                )
            )
        }
    }
}