package com.example.countriesapp.domain.model

import com.example.countriesapp.data.local.entity.CountryEntity

data class Country(
    val Iso2: String,
    val Iso3: String,
    val name: String
) {
    fun toCountryEntity(): CountryEntity {
        return CountryEntity(
            name = name
        )

    }
}
