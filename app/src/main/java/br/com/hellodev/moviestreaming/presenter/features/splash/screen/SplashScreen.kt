package br.com.hellodev.moviestreaming.presenter.features.splash.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.presenter.components.loading.LoadingScreenUI
import br.com.hellodev.moviestreaming.presenter.features.splash.action.SplashAction
import br.com.hellodev.moviestreaming.presenter.features.splash.viewmodel.SplashViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navigateToAppScreen: () -> Unit,
    navigateToWelcomeScreen: () -> Unit,
    navigateToHomeAuthenticationScreen: () -> Unit,
) {
    val viewModel = koinViewModel<SplashViewModel>()
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(state.isLoading) {
        if (!state.isLoading) {
            if (state.isWelcomeVisited) {
                if (state.isAuthenticated) {
                    navigateToAppScreen()
                } else {
                    navigateToHomeAuthenticationScreen()
                }
            } else {
                navigateToWelcomeScreen()
            }
        }
    }

    LaunchedEffect(true) {
        scope.launch {
            delay(2000)
            viewModel.submitAction(action = SplashAction.OnNextScreen)
        }
    }

    SplashContent()
}

@Composable
private fun SplashContent() {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
            )

            LoadingScreenUI(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview
@Composable
private fun SplashPreview() {
    SplashContent()
}