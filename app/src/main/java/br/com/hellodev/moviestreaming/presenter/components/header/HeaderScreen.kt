package br.com.hellodev.moviestreaming.presenter.components.header

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import br.com.hellodev.moviestreaming.presenter.theme.UrbanistFamily

@Composable
fun HeaderScreen(
    modifier: Modifier = Modifier,
    @StringRes title: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = stringResource(title),
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 28.8.sp,
                fontFamily = UrbanistFamily,
                fontWeight = FontWeight.Bold,
                color = MovieStreamingTheme.colorScheme.textColor
            )
        )
    }
}

@Preview
@Composable
private fun HeaderScreenPreview() {
    MovieStreamingTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MovieStreamingTheme.colorScheme.backgroundColor)
                .padding(24.dp)
        ) {
            HeaderScreen(
                title = R.string.label_account_bottom_app_bar
            )
        }
    }
}