package com.example.countriesapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryApiResponseDto(
    @SerializedName("data")
    val countries: List<CountryDto>,
    val error: Boolean,
    val msg: String
)