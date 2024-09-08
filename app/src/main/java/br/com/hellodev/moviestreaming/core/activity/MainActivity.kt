package br.com.hellodev.moviestreaming.core.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.moviestreaming.core.navigation.hosts.onboarding.OnboardingNavHost
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieStreamingTheme {
                OnboardingNavHost(navHostController = rememberNavController())
            }
        }
    }
}