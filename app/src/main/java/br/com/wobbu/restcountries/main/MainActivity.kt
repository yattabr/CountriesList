package br.com.wobbu.restcountries.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.wobbu.restcountries.R
import br.com.wobbu.restcountries.base.BaseApplication
import br.com.wobbu.restcountries.base.ViewModelFactory
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.data.Status
import br.com.wobbu.restcountries.model.Countries
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var countriesAdapter: CountriesAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as BaseApplication).getAppComponent().doInjection(this)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        initObserver()
        initUI()
        mainViewModel.fetchCountries()
    }

    private fun initUI() {
        edit_search.addTextChangedListener(filterWatcher)
    }

    private fun initObserver() {
        mainViewModel.fetchCountriesObserver.observe(this, Observer {
            when (it) {
                is ApiResponse -> countriesResponse(it)
            }
        })
    }

    private fun countriesResponse(apiResponse: ApiResponse) {
        when (apiResponse.status) {
            Status.LOADING -> loading.visibility = View.VISIBLE
            Status.SUCCESS -> {
                loading.visibility = View.GONE
                mountRecyclerView(apiResponse.data as ArrayList<Countries>)
            }
            Status.ERROR -> {
                loading.visibility = View.GONE
                Log.i("API_RESPONSE_ERROR", apiResponse.error.toString())
            }
        }
    }

    private fun mountRecyclerView(items: ArrayList<Countries>) {
        countriesAdapter.setActivityContext(this)
        countriesAdapter.loadCountries(items)
        recycler_view.adapter = countriesAdapter
    }

    private val filterWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            countriesAdapter.filter.filter(s)
        }
    }
}
