package br.com.hellodev.moviestreaming.presenter.features.country.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.hellodev.moviestreaming.R
import br.com.hellodev.moviestreaming.domain.remote.model.country.Country
import br.com.hellodev.moviestreaming.presenter.components.button.PrimaryButton
import br.com.hellodev.moviestreaming.presenter.components.divider.HorizontalDividerUI
import br.com.hellodev.moviestreaming.presenter.components.radio.RadioButtonUi
import br.com.hellodev.moviestreaming.presenter.components.textfield.default.TextFieldUI
import br.com.hellodev.moviestreaming.presenter.components.topAppBar.TopAppBarUI
import br.com.hellodev.moviestreaming.presenter.features.country.action.CountryAction
import br.com.hellodev.moviestreaming.presenter.features.country.state.CountryState
import br.com.hellodev.moviestreaming.presenter.features.country.viewmodel.CountryViewModel
import br.com.hellodev.moviestreaming.presenter.theme.MovieStreamingTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountryScreen(
    onCountrySelected: (Country?) -> Unit,
    onBackPressed: () -> Unit
) {
    val viewModel = koinViewModel<CountryViewModel>()
    val state by viewModel.state.collectAsState()

    CountryContent(
        state = state,
        action = viewModel::submitAction,
        onCountrySelected = onCountrySelected,
        onBackPressed = onBackPressed
    )
}

@Composable
fun CountryContent(
    state: CountryState,
    action: (CountryAction) -> Unit,
    onCountrySelected: (Country?) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarUI(
                title = stringResource(R.string.label_title_country_screen),
                onClick = onBackPressed
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .background(MovieStreamingTheme.colorScheme.primaryBackgroundColor.copy(alpha = 0.7f))
            ) {
                HorizontalDividerUI()

                PrimaryButton(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 24.dp,
                            bottom = 32.dp
                        ),
                    text = stringResource(R.string.label_button_select_country_screen),
                    enabled = state.selectedCountry != null,
                    onClick = { onCountrySelected(state.selectedCountry) }
                )
            }
        },
        containerColor = MovieStreamingTheme.colorScheme.primaryBackgroundColor,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                TextFieldUI(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = state.searchQuery,
                    placeholder = stringResource(R.string.label_search_placeholder_country_screen),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_line),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    },
                    onValueChange = {
                        action(CountryAction.OnSearch(it))
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        bottom = 24.dp,
                        top = 8.dp
                    )
                ) {
                    items(state.countriesFiltered) { country ->
                        RadioButtonUi(
                            selected = country == state.selectedCountry,
                            text = country.name ?: "",
                            onClick = {
                                action(CountryAction.OnCountrySelected(country))
                            }
                        )
                    }
                }
            }
        }
    )
}

@PreviewLightDark
@Composable
private fun CountryPreview() {
    MovieStreamingTheme {
        CountryContent(
            state = CountryState(
                countriesFiltered = Country.items
            ),
            action = {},
            onCountrySelected = {},
            onBackPressed = {}
        )
    }
}