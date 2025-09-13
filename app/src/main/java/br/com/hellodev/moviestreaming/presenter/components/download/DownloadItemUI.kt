package br.com.hellodev.moviestreaming.presenter.components.download

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.extensions.calculateFileSize
import br.com.hellodev.moviestreaming.domain.remote.model.movie.Movie
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun DownloadItemUI(
    modifier: Modifier = Modifier,
    movie: Movie,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ImageUI(
            modifier = Modifier
                .width(150.dp)
                .height(112.dp),
            imageModel = movie.backdropPath,
            contentScale = ContentScale.Crop,
            previewPlaceholder = painterResource(id = R.drawable.movie_placeholder),
            onClick = {}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(112.dp)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = movie.title ?: "",
                modifier = Modifier,
                style = TextStyle(
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(700),
                    color = MovieStreamingTheme.colorScheme.textColor
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "2h 28m 32s",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(600),
                    color = MovieStreamingTheme.colorScheme.textColor,
                    letterSpacing = 0.2.sp
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.runtime?.toFloat()?.calculateFileSize().orEmpty(),
                    modifier = Modifier
                        .background(
                            color = MovieStreamingTheme.colorScheme.alphaDefaultColor,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 8.dp
                        ),
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(600),
                        color = MovieStreamingTheme.colorScheme.defaultColor,
                        letterSpacing = 0.2.sp
                    )
                )

                IconButton(
                    modifier = Modifier
                        .size(20.dp),
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    },
                    onClick = onDeleteClick
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun DownloadItemUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
        ) {
            DownloadItemUI(
                movie = Movie(
                    title = "Sonic 3 - Sonic The Hedgehog",
                    runtime = 90
                ),
                onDeleteClick = {}
            )
        }
    }
}