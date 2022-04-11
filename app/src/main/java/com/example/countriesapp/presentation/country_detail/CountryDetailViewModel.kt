package com.example.countriesapp.presentation.country_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapp.common.Resource
import com.example.countriesapp.domain.use_case.get_country.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    savedStateHandle: SavedStateHandle

) : ViewModel() {
    private val _state = mutableStateOf(CountryDetailState())
    val state: State<CountryDetailState> = _state

    init {
        val s = savedStateHandle.get<String>("s")
        val name = savedStateHandle.get<String>("name")

        if (s != null) {
            if (name != null) {
                getCountry(s, name)
            }
        }
    }

    private fun getCountry(s: String, name: String) {
        getCountryUseCase(s, name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CountryDetailState(country = result.data)

                }
                is Resource.Error -> {
                    _state.value = CountryDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CountryDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}