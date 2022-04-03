package com.example.countriesapp.di

import com.example.countriesapp.data.remote.CountriesApi
import com.example.countriesapp.data.remote.CountryDetailsApi
import com.example.countriesapp.data.repository.CountryRepositoryImpl
import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.domain.use_case.get_countries.GetCountriesUseCase
import com.example.countriesapp.domain.use_case.get_country.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(repository: CountryRepository): GetCountriesUseCase {
        return GetCountriesUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideGetCountryUseCase(repository: CountryRepository): GetCountryUseCase {
        return GetCountryUseCase(repository = repository)
    }


    @Provides
    @Singleton
    fun provideCountryDetailsApi(): CountryDetailsApi {
        return Retrofit.Builder()
            .baseUrl(CountryDetailsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(CountriesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(countriesApi: CountriesApi, countryDetails: CountryDetailsApi): CountryRepository {
        return CountryRepositoryImpl(countriesApi, countryDetails)
    }

}