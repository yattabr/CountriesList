package br.com.wobbu.restcountries.di

import android.content.Context
import br.com.wobbu.restcountries.data.ApiCallInterface
import br.com.wobbu.restcountries.data.main.CountriesRepository
import br.com.wobbu.restcountries.domain.CountriesUseCase
import br.com.wobbu.restcountries.view.main.CountriesAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule(private val context: Context) {

    @Provides
    @Singleton
    internal open fun provideContext(): Context {
        return context
    }

    @Provides
    internal open fun countriesAdapter(): CountriesAdapter {
        return CountriesAdapter()
    }

    @Provides
    internal open fun getCountriesRepository(apiCallInterface: ApiCallInterface): CountriesRepository {
        return CountriesRepository(apiCallInterface)
    }
}