package br.com.wobbu.restcountries.data.main

import br.com.wobbu.restcountries.data.ApiCallInterface
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.model.Country
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CountriesRepository @Inject constructor(private val apiCallInterface: ApiCallInterface) :
    MainRepository {
    override fun fetchCountries(): Flow<ApiResponse<ArrayList<Country>>> = flow {
        emit(ApiResponse.Loading)
        val response = apiCallInterface.fetchCountries()
        if (response != null) {
            emit(ApiResponse.Success(response))
        } else
            emit(ApiResponse.Error("Error fetching countries"))
    }
}