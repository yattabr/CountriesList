package br.com.wobbu.restcountries.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.data.main.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(var repository: Repository) : ViewModel() {

    val fetchCountriesObserver = MutableLiveData<ApiResponse>()
    private val disposables = CompositeDisposable()

    fun fetchCountries() {
        disposables.add(repository.fetchCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { fetchCountriesObserver.setValue(ApiResponse.loading()) }
            .subscribe(
                { result -> fetchCountriesObserver.setValue(ApiResponse.success(result)) },
                { throwable -> fetchCountriesObserver.setValue(ApiResponse.error(throwable)) }
            ))
    }
}