package br.com.wobbu.restcountries.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.domain.CountriesUseCase
import br.com.wobbu.restcountries.model.Country
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var countriesUseCase: CountriesUseCase

    private val _fetchCountriesObserver = MutableLiveData<ArrayList<Country>>()
    var fetchCountriesObserver = _fetchCountriesObserver

    private val _loadingObserver = MutableLiveData<Boolean>()
    var loadingObserver = _loadingObserver

    private val _errorObserver = MutableLiveData<String>()
    var errorObserver = _errorObserver

    fun fetchCountries() {
        viewModelScope.launch {
            countriesUseCase.fetchCountries().collect {
                when (it) {
                    is ApiResponse.Loading -> _loadingObserver.value = true
                    is ApiResponse.Success -> {
                        _fetchCountriesObserver.value = it.data!!
                        _loadingObserver.value = false
                    }
                    is ApiResponse.Error -> {
                        _errorObserver.value = it.error
                        _loadingObserver.value = false
                    }
                }
            }
        }
    }
}