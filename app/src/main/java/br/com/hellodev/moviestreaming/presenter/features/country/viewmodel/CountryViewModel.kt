package br.com.hellodev.moviestreaming.presenter.features.country.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.moviestreaming.core.functions.normalize
import br.com.hellodev.moviestreaming.domain.remote.model.country.Country
import br.com.hellodev.moviestreaming.presenter.features.country.action.CountryAction
import br.com.hellodev.moviestreaming.presenter.features.country.state.CountryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val _state = MutableStateFlow(CountryState())
    val state = _state.asStateFlow()

    init {
        getCounties()
    }

    fun submitAction(action: CountryAction) {
        when (action) {
            is CountryAction.OnCountrySelected -> {
                onCountrySelected(country = action.country)
            }

            is CountryAction.OnSearch -> {
                searchCountry(query = action.query)
            }
        }
    }

    private fun onCountrySelected(country: Country) {
        _state.update { currentState ->
            currentState.copy(selectedCountry = country)
        }
    }

    private fun getCounties() {
        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    countries = Country.items,
                    countriesFiltered = Country.items
                )
            }
        }
    }

    private fun searchCountry(query: String) {
        val countriesFiltered = Country.items.filter { country ->
            country.name?.normalize()?.contains(query.normalize()) == true
        }

        _state.update { currentState ->
            currentState.copy(
                countriesFiltered = countriesFiltered,
                searchQuery = query
            )
        }
    }

}