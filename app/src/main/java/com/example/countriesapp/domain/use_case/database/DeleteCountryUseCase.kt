package com.example.countriesapp.domain.use_case.database

import com.example.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject

class DeleteCountryUseCase  @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(country: String?) {
        repository.deleteCountry(country = country)
    }
}