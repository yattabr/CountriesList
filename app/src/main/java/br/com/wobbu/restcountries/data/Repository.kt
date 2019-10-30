package br.com.wobbu.restcountries.data

import br.com.wobbu.restcountries.model.Countries
import com.google.gson.JsonElement
import io.reactivex.Observable


class Repository(private val apiCallInterface: ApiCallInterface) {
    fun fetchCountries(): Observable<ArrayList<Countries>> {
        return apiCallInterface.fetchCountries()
    }
}