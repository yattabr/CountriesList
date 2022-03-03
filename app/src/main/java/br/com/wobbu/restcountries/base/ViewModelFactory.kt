package br.com.wobbu.restcountries.base

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.wobbu.restcountries.data.main.Repository
import br.com.wobbu.restcountries.view.main.MainViewModel
import javax.inject.Inject

open class ViewModelFactory : ViewModelProvider.Factory {

    var repository: Repository

    @Inject
    constructor(repository: Repository) {
        this.repository = repository
    }

    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}