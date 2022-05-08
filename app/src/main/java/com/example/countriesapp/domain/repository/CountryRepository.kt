package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.local.entity.CountryEntity
import com.example.countriesapp.data.remote.dto.CountryApiResponseDto

import com.example.countriesapp.data.remote.dto.CountryDetailsApiResponseDto

interface CountryRepository {
  //API
    suspend fun getCountries(): CountryApiResponseDto
    suspend fun getCountryByName(s: String, name: String): CountryDetailsApiResponseDto

    //Database
    suspend fun getSavedCountries(): List<CountryEntity>
    suspend fun upsertCountry(country: String?)
    suspend fun deleteCountry(country: String?)


}