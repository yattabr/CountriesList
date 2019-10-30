package br.com.wobbu.restcountries.di

import br.com.wobbu.restcountries.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, UtilsModule::class])
@Singleton
interface AppComponent {
    fun doInjection(mainActivity: MainActivity)
}
