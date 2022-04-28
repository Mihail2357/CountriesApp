package com.example.countriesapp.presentation.country_list

import com.example.countriesapp.domain.model.Country

data class CountryListState (
    val isLoading: Boolean=false,
    val searchQuery: String = "",
    val countries: List<Country> = emptyList(),
    val isRefreshing: Boolean = false,
    val error: String = ""
)