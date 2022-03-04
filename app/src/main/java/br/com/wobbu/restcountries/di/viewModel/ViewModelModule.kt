package br.com.wobbu.restcountries.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.wobbu.restcountries.base.ViewModelFactory
import br.com.wobbu.restcountries.domain.CountriesUseCase
import br.com.wobbu.restcountries.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun countriesViewModel(viewModel: MainViewModel): ViewModel
}