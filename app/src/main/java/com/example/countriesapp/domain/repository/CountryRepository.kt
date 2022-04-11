package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.remote.dto.CountryApiResponseDto

import com.example.countriesapp.data.remote.dto.CountryDetailsApiResponseDto

interface CountryRepository {

    suspend fun getCountries(): CountryApiResponseDto

    suspend fun getCountryByName(s: String, name: String): CountryDetailsApiResponseDto

}