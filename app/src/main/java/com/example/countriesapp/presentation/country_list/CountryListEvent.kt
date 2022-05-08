package com.example.countriesapp.presentation.country_list

sealed class CountryListEvent{
    data class OnSearchQueryChange(val query: String): CountryListEvent()
    data class SaveCountry(val country: String?): CountryListEvent()
    data class DeleteCountry(val country: String?): CountryListEvent()


}
