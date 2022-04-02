package com.example.countriesapp.data.remote.dto

import com.example.countriesapp.domain.model.Country

data class Data(
    val Iso2: String,
    val Iso3: String,
    val name: String
)

fun Data.toCountry(): Country {
    return Country(
        Iso2 = Iso2,
        Iso3 = Iso3,
        name = name
    )
}