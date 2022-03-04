package br.com.wobbu.restcountries.di

import android.app.Application
import br.com.wobbu.restcountries.di.viewModel.ViewModelModule
import br.com.wobbu.restcountries.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, UtilsModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {
    fun inject(application: Application)
    fun inject(mainActivity: MainActivity)
}
