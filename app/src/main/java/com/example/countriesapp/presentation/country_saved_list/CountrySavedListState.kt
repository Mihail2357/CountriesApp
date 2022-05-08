package com.example.countriesapp.presentation.country_saved_list

import com.example.countriesapp.domain.model.Country

data class CountrySavedListState (
    val isLoading: Boolean=false,
    val searchQuery: String = "",
    val countries: List<String?> = emptyList(),
    val isRefreshing: Boolean = false,
    val error: String = ""
)