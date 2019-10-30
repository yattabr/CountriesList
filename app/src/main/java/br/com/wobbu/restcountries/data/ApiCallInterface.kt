package br.com.wobbu.restcountries.data

import br.com.wobbu.restcountries.model.Countries
import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiCallInterface {

    @GET(Urls.FETCH_COUNTRIES)
    fun fetchCountries(): Observable<ArrayList<Countries>>
}