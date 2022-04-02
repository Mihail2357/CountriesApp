package com.example.countriesapp.presentation

sealed class Screen(val route: String) {
    object CountryListScreen: Screen("country_list_screen")
    object CountryDetailScreen: Screen("country_detail_screen")

}
