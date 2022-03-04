package br.com.wobbu.restcountries.view.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.wobbu.restcountries.R
import br.com.wobbu.restcountries.base.BaseApplication
import br.com.wobbu.restcountries.base.ViewModelFactory
import br.com.wobbu.restcountries.model.Country
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var countriesAdapter: CountriesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as BaseApplication).component.inject(this)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        initObserver()
        initUI()
        mainViewModel.fetchCountries()
    }

    private fun initUI() {
        edit_search.addTextChangedListener(filterWatcher)
    }

    private fun initObserver() {
        mainViewModel.fetchCountriesObserver.observe(this, Observer {
            mountRecyclerView(it)
        })

        mainViewModel.loadingObserver.observe(this, {
            if (it) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }
        })

        mainViewModel.errorObserver.observe(this, {
            Log.i("API_RESPONSE_ERROR", it)
        })
    }

    private fun mountRecyclerView(items: ArrayList<Country>) {
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
