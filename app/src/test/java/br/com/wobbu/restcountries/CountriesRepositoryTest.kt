package br.com.wobbu.restcountries

import br.com.wobbu.restcountries.data.ApiCallInterface
import br.com.wobbu.restcountries.data.ApiResponse
import br.com.wobbu.restcountries.data.main.CountriesRepository
import br.com.wobbu.restcountries.model.Country
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountriesRepositoryTest {

    lateinit var countriesRepository: CountriesRepository

    @Mock
    lateinit var apiCallInterface: ApiCallInterface

    @Mock
    lateinit var countries: ArrayList<Country>

    @Before
    fun setup() {
        countriesRepository = CountriesRepository(apiCallInterface)
    }

    @Test
    fun fetchCountriesSuccess() = runBlocking {
        whenever(apiCallInterface.fetchCountries()).thenReturn((countries))
        val response = countriesRepository.fetchCountries().toList()

        assertEquals(response, listOf(ApiResponse.Loading, ApiResponse.Success(countries)))
    }

    @Test
    fun fetchCountriesError() = runBlocking {
        whenever(apiCallInterface.fetchCountries()).thenReturn((null))
        val response = countriesRepository.fetchCountries().toList()

        assertEquals(
            response,
            listOf(ApiResponse.Loading, ApiResponse.Error("Error fetching countries"))
        )
    }
}