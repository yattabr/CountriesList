package br.com.wobbu.restcountries.domain

import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.data.main.CountriesRepository
import br.com.wobbu.restcountries.model.Country
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class CountriesUseCase @Inject constructor() {

    @Inject
    lateinit var repository: CountriesRepository

    fun fetchCountries(): Flow<ApiResponse<ArrayList<Country>>> {
        return repository.fetchCountries()
    }

}