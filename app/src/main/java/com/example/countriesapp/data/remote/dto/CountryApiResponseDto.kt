package com.example.countriesapp.data.remote.dto

data class CountryApiResponseDto(
    val data: List<Data>,
    val error: Boolean,
    val msg: String
)