package com.example.countriesapp.data.remote

import com.example.countriesapp.data.remote.dto.CountryApiResponseDto
import com.example.countriesapp.data.remote.dto.CountryDetailsApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CountriesApi {

    @GET("v0.1/countries/iso")
    suspend fun getCountries(): CountryApiResponseDto

    companion object {
        const val BASE_URL = "https://countriesnow.space/api/"
    }
}

interface CountryDetailsApi {

    @GET("v3.1/name/{name}")
    suspend fun getCountryByName(@Path("name") name: String): CountryDetailsApiResponseDto

    companion object {
        const val BASE_URL = "https://restcountries.com/"
    }

}