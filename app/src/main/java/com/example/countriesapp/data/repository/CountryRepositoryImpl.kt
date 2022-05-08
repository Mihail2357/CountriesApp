package com.example.countriesapp.data.repository

import com.example.countriesapp.data.local.CountryDao
import com.example.countriesapp.data.local.entity.CountryEntity
import com.example.countriesapp.data.remote.CountriesApi
import com.example.countriesapp.data.remote.CountryDetailsApi
import com.example.countriesapp.data.remote.dto.CountryApiResponseDto
import com.example.countriesapp.data.remote.dto.CountryDetailsApiResponseDto
import com.example.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
        private val countriesApi: CountriesApi,
        private val countryDetails: CountryDetailsApi,
        private val dao: CountryDao
) : CountryRepository {
        override suspend fun getCountries(): CountryApiResponseDto {
                return countriesApi.getCountries()
        }
        override suspend fun getCountryByName(s: String, name: String): CountryDetailsApiResponseDto {
                return countryDetails.getCountryByName(s, name)
        }


        override suspend fun getSavedCountries(): List<CountryEntity> {
                return dao.getAllCountries()
        }

        override suspend fun upsertCountry(country: String?) {
                dao.upsert(country = CountryEntity(name=country))
        }
        override suspend fun deleteCountry(country: String?) {
                dao.deleteCountry(country=country)
        }

}


