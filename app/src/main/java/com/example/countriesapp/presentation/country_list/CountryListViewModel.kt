package com.example.countriesapp.presentation.country_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.common.Resource
import com.example.countriesapp.domain.model.Country
import com.example.countriesapp.domain.repository.CountryRepository
import com.example.countriesapp.domain.use_case.database.SaveCountryUseCase
import com.example.countriesapp.domain.use_case.get_countries.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val saveCountryUseCase: SaveCountryUseCase,

): ViewModel() {

    private val _state = mutableStateOf(CountryListState())
    val state: State<CountryListState> = _state
    var state2  by  mutableStateOf(CountryListState())

    private var searchJob: Job? = null

    init {
        getCountries()
    }

    fun onEvent(event: CountryListEvent) {
        when(event) {

            is CountryListEvent.SaveCountry -> {
                viewModelScope.launch {
                    saveCountryUseCase(event.country)
                }
            }
            is CountryListEvent.OnSearchQueryChange -> {
                state2 = state2.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCountries()
                }
            }
        }
    }

    private fun getCountries() {
        getCountriesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CountryListState(countries = result.data ?: emptyList() )
                    if(state2.searchQuery.equals("")) {
                        state2 = state2.copy(countries = result.data ?: emptyList())
                    }
                    else
                    {
                        val countries2 = result.data ?: emptyList()
                        val countries3 = mutableListOf<Country>()
                        for (country in countries2)
                            if (country.name.equals(state2.searchQuery))
                                countries3.add(country)

                        state2 = state2.copy(countries = countries3)
                    }

                }
                is Resource.Error -> {
                    _state.value = CountryListState(error = result.message ?:
                    "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CountryListState(isLoading = true )
                }
            }
        }.launchIn(viewModelScope)
    }

}