package br.com.hellodev.moviestreaming.presenter.features.country.action

import br.com.hellodev.moviestreaming.domain.remote.model.country.Country

sealed class CountryAction {
    data class OnCountrySelected(val country: Country) : CountryAction()
    data class OnSearch(val query: String) : CountryAction()
}