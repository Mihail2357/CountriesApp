package com.example.countriesapp.domain.use_case.get_saved_countries

import com.example.countriesapp.common.Resource
import com.example.countriesapp.data.remote.dto.toCountry
import com.example.countriesapp.domain.model.Country
import com.example.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetSavedCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {

    operator fun invoke(): Flow<Resource<List<String?>>> = flow {
        try{
            emit(Resource.Loading<List<String?>>())
            val countries = repository.getSavedCountries().map {it.name}
            emit(Resource.Success<List<String?>>(countries))
        } catch(e: HttpException) {
            emit(Resource.Error<List<String?>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<List<String?>>("Couldn't reach server. Check your internet connection."))
        }
    }
}
