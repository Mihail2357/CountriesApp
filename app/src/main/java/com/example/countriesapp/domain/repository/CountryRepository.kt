package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.remote.dto.CountryApiResponseDto
import com.example.countriesapp.data.remote.dto.CountryDetailDto

interface CountryRepository {

    suspend fun getCountries(): CountryApiResponseDto

    suspend fun getCountryByName(name: String): CountryDetailDto
}