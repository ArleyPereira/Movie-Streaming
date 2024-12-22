package br.com.hellodev.moviestreaming.presenter.components.textfield.click

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun TextFieldClickUI(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String,
    painter: Painter,
    isError: Boolean = false,
    enabled: Boolean = true,
    error: String = "",
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled,
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (isError) {
                        MovieStreamingTheme.colorScheme.alphaDefaultColor
                    } else MovieStreamingTheme.colorScheme.textFieldBackgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.dp,
                    color = if (isError) {
                        MovieStreamingTheme.colorScheme.defaultColor
                    } else {
                        Color.Transparent
                    },
                    shape = RoundedCornerShape(12.dp)
                )
                .height(56.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = TextStyle(
                        lineHeight = 19.6.sp,
                        fontFamily = UrbanistFamily,
                        color = MovieStreamingTheme.colorScheme.greyscale500Color,
                        letterSpacing = 0.2.sp
                    )
                )
            } else {
                Text(
                    text = value,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.4.sp,
                        fontFamily = UrbanistFamily,
                        fontWeight = FontWeight(600),
                        color = MovieStreamingTheme.colorScheme.textColor,
                        letterSpacing = 0.2.sp
                    )
                )
            }

            Icon(
                painter = painter,
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

        if (isError) {
            Text(
                text = error,
                modifier = Modifier
                    .padding(start = 16.dp, top = 4.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 19.6.sp,
                    fontFamily = UrbanistFamily,
                    color = MovieStreamingTheme.colorScheme.defaultColor,
                    letterSpacing = 0.2.sp
                )
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TextFieldClickUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor)
        ) {
            TextFieldClickUI(
                modifier = Modifier
                    .padding(32.dp),
                value = "Masculino",
                placeholder = "Gênero",
                painter = painterResource(id = R.drawable.ic_right),
                error = "Gênero inválido",
                isError = true,
                onClick = {}
            )
        }
    }
}