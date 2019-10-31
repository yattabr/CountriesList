package br.com.wobbu.restcountries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.data.Repository
import br.com.wobbu.restcountries.model.Country
import br.com.wobbu.restcountries.view.main.MainViewModel
import com.nhaarman.mockitokotlin2.refEq
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var repository: Repository
    @Mock
    lateinit var observer: Observer<Any>
    @Mock
    lateinit var countries: ArrayList<Country>
    @Mock
    lateinit var serverError: Throwable
    private var mainViewModel: MainViewModel? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }
        mainViewModel = MainViewModel(repository)
        mainViewModel!!.fetchCountriesObserver.observeForever(observer)
    }

    @Test
    fun testAPIFetchDataSuccess() {
        Mockito.`when`(repository.fetchCountries()).thenReturn(Observable.just(countries))
        mainViewModel!!.fetchCountries()
        Mockito.verify(observer).onChanged(refEq(ApiResponse.loading()))
        Mockito.verify(observer).onChanged(refEq(ApiResponse.success(countries)))
    }

    @Test
    fun testAPIFetchDataError() {
        Mockito.`when`(repository.fetchCountries()).thenReturn(Observable.error(serverError))
        mainViewModel!!.fetchCountries()
        Mockito.verify(observer).onChanged(refEq(ApiResponse.loading()))
        Mockito.verify(observer).onChanged(refEq(ApiResponse.error(serverError)))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        mainViewModel = null
    }
}
