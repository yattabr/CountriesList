package br.com.wobbu.restcountries.model

import java.io.Serializable

class Country : Serializable {
    var name: Name = Name()
    var capital: ArrayList<String> = arrayListOf()
    var region: String = ""
    var population: String = ""
    var flags: Flags = Flags()
    var latlng: ArrayList<Double> = arrayListOf()

    data class Name(var common: String = "") : Serializable
    data class Flags(var png: String = "", var svg: String = "") : Serializable
}