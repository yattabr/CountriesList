package br.com.wobbu.restcountries.di

import android.content.Context
import br.com.wobbu.restcountries.main.CountriesAdapter
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
}