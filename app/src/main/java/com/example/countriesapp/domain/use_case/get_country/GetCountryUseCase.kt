package com.example.countriesapp.domain.use_case.get_country

import com.example.countriesapp.common.Resource
import com.example.countriesapp.data.remote.dto.toCountryDetail
import com.example.countriesapp.domain.model.CountryDetail
import com.example.countriesapp.domain.repository.CountryRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetCountryUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    operator fun invoke(s: String, name: String): Flow<Resource<CountryDetail>> = flow {
        try {
            emit(Resource.Loading<CountryDetail>())
            var k=0
            val setA = setOf("Romania")
            if (setA.contains(name)) k=1
            val country = repository.getCountryByName(s, name)[k].toCountryDetail()
            emit(Resource.Success<CountryDetail>(country))
        } catch (e: HttpException) {
            emit(Resource.Error<CountryDetail>("An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<CountryDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}
