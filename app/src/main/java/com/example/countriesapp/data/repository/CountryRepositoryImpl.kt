package com.example.countriesapp.data.repository

import com.example.countriesapp.data.remote.CountriesApi
import com.example.countriesapp.data.remote.dto.CountryApiResponseDto
import com.example.countriesapp.data.remote.dto.CountryDetailDto
import com.example.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
        private val api: CountriesApi
) : CountryRepository {
        override suspend fun getCountries(): CountryApiResponseDto {
                return api.getCountries()
        }
        override suspend fun getCountryByName(name: String): CountryDetailDto {
                return api.getCountryByName(name)
        }
}


