package br.com.hellodev.moviestreaming.presenter.components.image

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.theme.DefaultColor
import br.com.hellodev.moviestreaming.presenter.theme.Greyscale500Color
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ImageUI(
    modifier: Modifier = Modifier,
    imageModel: Any,
    previewPlaceholder: Painter? = null,
    shape: Shape = RoundedCornerShape(12.dp),
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent),
    contentScale: ContentScale = ContentScale.Crop,
    onCLick: () -> Unit
) {
    val errorComposition by rememberLottieComposition(LottieCompositionSpec.Asset("error_loading_error.json"))

    CoilImage(
        imageModel = { imageModel },
        modifier = modifier
            .clip(shape = shape)
            .clickable { onCLick() }
            .border(border = borderStroke, shape = shape),
        imageOptions = ImageOptions(
            contentScale = contentScale
        ),
        previewPlaceholder = previewPlaceholder,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = MovieStreamingTheme.colorScheme.defaultColor
                    )
                }
            }
        },
        failure = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                LottieAnimation(
                    composition = errorComposition,
                    modifier = Modifier
                        .size(125.dp)
                        .align(Alignment.Center),
                    iterations = LottieConstants.IterateForever,
                    maintainOriginalImageBounds = true
                )
            }
        }
    )
}

@Preview
@Composable
private fun ImageUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieStreamingTheme.colorScheme.backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageUI(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp),
                imageModel = "https:f/f452aa2c-0c64-4bbf-b065-942b8dbda8bb/dgwisle-0c7c80d4-7297-41aa-b9eb-0f13f7a99f99.png/v1/fill/w_1280,h_1929,q_80,strp/venom_3_by_diamonddead_art_dgwisle-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTkyOSIsInBhdGgiOiJcL2ZcL2Y0NTJhYTJjLTBjNjQtNGJiZi1iMDY1LTk0MmI4ZGJkYThiYlwvZGd3aXNsZS0wYzdjODBkNC03Mjk3LTQxYWEtYjllYi0wZjEzZjdhOTlmOTkucG5nIiwid2lkdGgiOiI8PTEyODAifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.AR1eGE7ys-s-ZgEBtr3_hgE1obqA04NPpfiFwG4XHiI",
                previewPlaceholder = painterResource(R.drawable.placeholder_welcome),
                shape = CircleShape,
                borderStroke = BorderStroke(3.dp, Greyscale500Color),
                onCLick = {}
            )
        }
    }
}