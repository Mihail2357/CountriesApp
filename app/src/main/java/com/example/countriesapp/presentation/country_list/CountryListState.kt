package com.example.countriesapp.presentation.country_list

import com.example.countriesapp.domain.model.Country

data class CountryListState (
    val isLoading: Boolean=false,
    val countries: List<Country> = emptyList(),
    val error: String = ""
)