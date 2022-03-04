package br.com.wobbu.restcountries.data

import br.com.wobbu.restcountries.model.Country
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiCallInterface {

    @GET(Urls.FETCH_COUNTRIES)
    suspend fun fetchCountries(): ArrayList<Country>?
}