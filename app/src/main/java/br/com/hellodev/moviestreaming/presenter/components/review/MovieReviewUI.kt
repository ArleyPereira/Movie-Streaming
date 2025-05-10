package br.com.hellodev.moviestreaming.presenter.components.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.image.ImageUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun MovieReviewUI(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImageUI(
                modifier = Modifier
                    .size(48.dp),
                imageModel = "",
                contentScale = ContentScale.Crop,
                previewPlaceholder = painterResource(id = R.drawable.movie_placeholder),
                shape = CircleShape,
                onClick = {}
            )

            Text(
                text = "Kristin Watson",
                style = TextStyle(
                    lineHeight = 22.4.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(700),
                    color = MovieStreamingTheme.colorScheme.textColor,
                    letterSpacing = 0.2.sp
                )
            )
        }

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna.",
            style = TextStyle(
                lineHeight = 19.6.sp,
                fontFamily = UrbanistFamily,
                color = MovieStreamingTheme.colorScheme.textColor,
                letterSpacing = 0.2.sp,
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
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
                    text = "8",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(500),
                        color = MovieStreamingTheme.colorScheme.defaultColor,
                        letterSpacing = 0.2.sp
                    )
                )
            }

            Text(
                text = "3 days ago",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = UrbanistFamily,
                    fontWeight = FontWeight(500),
                    color = MovieStreamingTheme.colorScheme.textColor,
                    letterSpacing = 0.2.sp,
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun MovieReviewUIPreview() {
    MovieStreamingTheme {
        MovieReviewUI()
    }
}