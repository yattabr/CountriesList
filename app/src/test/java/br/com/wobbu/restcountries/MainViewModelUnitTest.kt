package br.com.wobbu.restcountries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.domain.CountriesUseCase
import br.com.wobbu.restcountries.model.Country
import br.com.wobbu.restcountries.view.main.MainViewModel
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MainViewModelUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCase: CountriesUseCase

    @Mock
    lateinit var loadingObserver: Observer<Any>

    @Mock
    lateinit var fetchObserver: Observer<Any>

    @Mock
    lateinit var errorObserver: Observer<Any>

    @Mock
    lateinit var countries: ArrayList<Country>

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel()
        mainViewModel.countriesUseCase = useCase
        mainViewModel.fetchCountriesObserver.observeForever(fetchObserver)
        mainViewModel.loadingObserver.observeForever(loadingObserver)
        mainViewModel.errorObserver.observeForever(errorObserver)
    }

    @Test
    fun testAPIFetchDataSuccess() {
        runBlocking {
            val flow = flow {
                emit(ApiResponse.Loading)
                emit(ApiResponse.Success(countries))
            }
            whenever(useCase.fetchCountries()).thenReturn((flow as Flow<ApiResponse<ArrayList<Country>>>?))
            mainViewModel.fetchCountries()

            flow.collect {
                verify(loadingObserver).onChanged(true)
                verify(fetchObserver).onChanged(countries)
            }
        }
    }

    @Test
    fun testAPIFetchDataError() {
        runBlocking {
            val flow = flow {
                emit(ApiResponse.Loading)
                emit(ApiResponse.Error("error"))
            }

            whenever(useCase.fetchCountries()).thenReturn((flow as Flow<ApiResponse<ArrayList<Country>>>?))
            mainViewModel.fetchCountries()

            flow.collect {
                verify(loadingObserver).onChanged(false)
                verify(errorObserver).onChanged("error")
            }
        }
    }
}
