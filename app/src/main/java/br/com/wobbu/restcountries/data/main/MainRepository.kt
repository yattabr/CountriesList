package br.com.wobbu.restcountries.data.main

import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.model.Country
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface MainRepository {
    fun fetchCountries(): Flow<ApiResponse<ArrayList<Country>>>
}