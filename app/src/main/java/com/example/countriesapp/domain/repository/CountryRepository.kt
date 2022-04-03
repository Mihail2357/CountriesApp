package com.example.countriesapp.domain.repository

import com.example.countriesapp.data.remote.dto.CountryApiResponseDto

import com.example.countriesapp.data.remote.dto.CountryDetailsApiResponseDto
import com.example.countriesapp.data.remote.dto.a.CountryApiResponseDto2

interface CountryRepository {

    suspend fun getCountries(): CountryApiResponseDto

    suspend fun getCountryByName(name: String): CountryDetailsApiResponseDto
}