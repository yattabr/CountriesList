package br.com.wobbu.restcountries.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class MockAppModule(private val context: Context) : AppModule(context) {

    @Provides
    @Singleton
    override fun provideContext(): Context {
        return context
    }
}