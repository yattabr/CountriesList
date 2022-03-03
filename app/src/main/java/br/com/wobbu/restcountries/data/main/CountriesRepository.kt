package br.com.wobbu.restcountries.data.main

import br.com.wobbu.restcountries.data.ApiCallInterface
import br.com.wobbu.restcountries.model.Country
import io.reactivex.Observable


class Repository(private val apiCallInterface: ApiCallInterface) {
    fun fetchCountries(): Observable<ArrayList<Country>> {
        return apiCallInterface.fetchCountries()
    }
}