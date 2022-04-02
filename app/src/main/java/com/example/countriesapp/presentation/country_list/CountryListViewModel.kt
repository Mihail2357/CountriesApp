package com.example.countriesapp.presentation.country_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.common.Resource
import com.example.countriesapp.domain.use_case.get_countries.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
): ViewModel() {

    private val _state = mutableStateOf(CountryListState())
    val state: State<CountryListState> = _state

    init {
        getCountries()
    }

    private fun getCountries() {
        getCountriesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CountryListState(countries = result.data ?: emptyList() )

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