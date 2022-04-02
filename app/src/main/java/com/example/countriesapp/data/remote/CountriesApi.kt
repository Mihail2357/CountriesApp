package com.example.countriesapp.data.remote

import com.example.countriesapp.data.remote.dto.CountryApiResponseDto
import com.example.countriesapp.data.remote.dto.CountryDetailDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CountriesApi {

    @GET("/v0.1/countries/iso/")
    suspend fun getCountries(): CountryApiResponseDto

    @GET("/v3.1/name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): CountryDetailDto

    companion object {
        const val BASE_URL = "https://restcountries.com/"
    }

}