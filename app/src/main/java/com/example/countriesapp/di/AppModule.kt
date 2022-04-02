package com.example.countriesapp.di

import com.example.countriesapp.data.remote.CountriesApi
import com.example.countriesapp.data.repository.CountryRepositoryImpl
import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.domain.use_case.get_countries.GetCountriesUseCase
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
    fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(CountriesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(api: CountriesApi): CountryRepository {
        return CountryRepositoryImpl(api)
    }

}