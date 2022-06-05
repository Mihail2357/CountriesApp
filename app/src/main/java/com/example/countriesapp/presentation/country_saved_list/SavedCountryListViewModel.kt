package com.example.countriesapp.presentation.country_saved_list

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.common.Resource
import com.example.countriesapp.domain.use_case.database.DeleteCountryUseCase
import com.example.countriesapp.domain.use_case.get_countries.GetCountriesUseCase
import com.example.countriesapp.domain.use_case.get_saved_countries.GetSavedCountriesUseCase
import com.example.countriesapp.presentation.country_list.CountryListEvent
import com.example.countriesapp.presentation.country_list.CountryListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedCountryListViewModel @Inject constructor(
    private val getCountriesUseCase: GetSavedCountriesUseCase,
    private val deleteCountryUseCase: DeleteCountryUseCase
): ViewModel() {

    private val _state = mutableStateOf(CountrySavedListState())
    val state: State<CountrySavedListState> = _state
    var state2  by  mutableStateOf(CountrySavedListState())

    private var searchJob: Job? = null

    init {
        getCountries()
    }

    fun onEvent(event: CountryListEvent) {
        when(event) {
            is CountryListEvent.DeleteCountry -> {
                viewModelScope.launch {
                    deleteCountryUseCase(event.country)
                }
                val countries_after_delete = mutableListOf<String?>()
                for(country in state2.countries)
                    if (!country.equals(event.country))
                        countries_after_delete.add(country)

                state2 = state2.copy(countries = countries_after_delete)

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
                    _state.value = CountrySavedListState(countries = result.data ?: emptyList() )
                    if(state2.searchQuery.equals("")) {
                        state2 = state2.copy(countries = result.data ?: emptyList())
                    }
                    else
                    {
                        val countries2 = result.data ?: emptyList()
                        val countries3 = mutableListOf<String?>()
                        for (country in countries2)
                            if (country.equals(state2.searchQuery))
                                countries3.add(country)

                        state2 = state2.copy(countries = countries3)
                    }

                }
                is Resource.Error -> {
                    _state.value = CountrySavedListState(error = result.message ?:
                    "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CountrySavedListState(isLoading = true )
                }
            }
        }.launchIn(viewModelScope)
    }

}