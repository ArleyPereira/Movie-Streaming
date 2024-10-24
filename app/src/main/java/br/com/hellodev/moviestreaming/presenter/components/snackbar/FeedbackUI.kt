package br.com.hellodev.moviestreaming.presenter.components.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType
import br.com.hellodev.moviestreaming.core.enums.feedback.FeedbackType.*
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun FeedbackUI(
    modifier: Modifier = Modifier,
    message: String,
    type: FeedbackType
) {
    val defaultColor = when (type) {
        SUCCESS -> MovieStreamingTheme.colorScheme.successColor
        INFO -> MovieStreamingTheme.colorScheme.infoColor
        WARNING -> MovieStreamingTheme.colorScheme.warningColor
        ERROR -> MovieStreamingTheme.colorScheme.alertColor
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = defaultColor.copy(alpha = 0.2f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_info),
                contentDescription = message,
                tint = defaultColor
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = message,
                style = TextStyle(
                    fontFamily = UrbanistFamily,
                    color = defaultColor,
                    letterSpacing = 0.2.sp
                )
            )
        }
    }
}

@Preview
@Composable
private fun FeedbackUIPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = SUCCESS
            )

            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = INFO
            )

            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = WARNING
            )

            FeedbackUI(
                message = "Login efetuado com sucesso",
                type = ERROR
            )
        }
    }
}