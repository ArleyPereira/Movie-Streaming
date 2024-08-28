package br.com.hellodev.moviestreaming.core.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.hellodev.moviestreaming.presenter.screens.authentication.signup.screen.SignupScreen
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieStreamingTheme {
                Scaffold(
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()

                                //.padding(paddingValues)
                        ) {
                            SignupScreen(
                                onBackPressed = {}
                            )
                        }
                    }
                )
                //WelcomeScreen()
            }
        }
    }
}