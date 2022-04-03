package com.example.countriesapp.domain.use_case.get_countries

import com.example.countriesapp.common.Resource
import com.example.countriesapp.data.remote.dto.toCountry
import com.example.countriesapp.domain.model.Country
import com.example.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {

    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try{
            emit(Resource.Loading<List<Country>>())
            val countries = repository.getCountries().countries.map {it.toCountry()}
            emit(Resource.Success<List<Country>>(countries))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Country>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Country>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
