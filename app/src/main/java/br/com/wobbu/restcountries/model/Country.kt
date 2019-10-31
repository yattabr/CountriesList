package br.com.wobbu.restcountries.model

import java.io.Serializable

class Country : Serializable {
    var name: String = ""
    var capital: String = ""
    var region: String = ""
    var population: String = ""
    var flag: String = ""
    var latlng: ArrayList<Double> = arrayListOf()
}