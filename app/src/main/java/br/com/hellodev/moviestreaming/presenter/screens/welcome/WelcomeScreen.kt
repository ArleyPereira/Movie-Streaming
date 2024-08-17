package br.com.hellodev.moviestreaming.presenter.screens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.slide.WelcomeSlideUI
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

@Composable
fun WelcomeScreen() {
    WelcomeContent()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeContent() {
    val slideItems = listOf(
        Pair(
            first = "Bem-vindo",
            second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
        ),
        Pair(
            first = "Bem-vindo",
            second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
        ),
        Pair(
            first = "Bem-vindo",
            second = "O melhor aplicativo de streaming de filmes do século para tornar seus dias incríveis!"
        )
    )
    val pagerState = rememberPagerState {
        slideItems.size
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = paddingValues.calculateBottomPadding())
                    .background(MovieStreamingTheme.colorScheme.backgroundColor),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.placeholder_welcome),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(id = R.drawable.background_gradient),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    WelcomeSlideUI(
                        modifier = Modifier
                            .weight(1f),
                        slideItems = slideItems,
                        pagerState = pagerState
                    )

                    PrimaryButton(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                        text = "Pular",
                        onClick = {}
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun WelcomePreview() {
    WelcomeContent()
}