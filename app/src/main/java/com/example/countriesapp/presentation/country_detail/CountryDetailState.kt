package com.example.countriesapp.presentation.country_detail

import com.example.countriesapp.domain.model.Country
import com.example.countriesapp.domain.model.CountryDetail

data class CountryDetailState (
    val isLoading: Boolean = false,
    val country: CountryDetail? = null,
    val error: String = ""
)