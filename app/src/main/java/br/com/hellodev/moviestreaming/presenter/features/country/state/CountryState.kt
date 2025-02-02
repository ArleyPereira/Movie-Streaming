package br.com.hellodev.moviestreaming.presenter.features.country.state

import br.com.hellodev.moviestreaming.domain.remote.model.country.Country

data class CountryState(
    val selectedCountry: Country? = null,
    val searchQuery: String = "",
    val countries: List<Country> = emptyList(),
    val countriesFiltered: List<Country> = emptyList()
)
