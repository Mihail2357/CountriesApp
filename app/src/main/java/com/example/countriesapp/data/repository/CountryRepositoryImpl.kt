package com.example.countriesapp.data.repository

import com.example.countriesapp.data.remote.CountriesApi
import com.example.countriesapp.data.remote.CountryDetailsApi
import com.example.countriesapp.data.remote.dto.CountryApiResponseDto
import com.example.countriesapp.data.remote.dto.CountryDetailsApiResponseDto
import com.example.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
        private val countriesApi: CountriesApi,
        private val countryDetails: CountryDetailsApi
) : CountryRepository {
        override suspend fun getCountries(): CountryApiResponseDto {
                return countriesApi.getCountries()
        }
        override suspend fun getCountryByName(name: String): CountryDetailsApiResponseDto {
                return countryDetails.getCountryByName(name)
        }
}


