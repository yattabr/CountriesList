package br.com.wobbu.restcountries.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.data.Repository
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

//    fun convertJsonToRepositories(json: String): ArrayList<Repositories> {
//        return Gson().fromJson(json, Array<Repositories>::class.java).toCollection(ArrayList())
//    }

}