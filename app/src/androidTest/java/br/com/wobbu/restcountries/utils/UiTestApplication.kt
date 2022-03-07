package br.com.wobbu.restcountries.utils

import br.com.wobbu.restcountries.base.BaseApplication
import br.com.wobbu.restcountries.di.AppComponent
import br.com.wobbu.restcountries.di.DaggerAppComponent
import br.com.wobbu.restcountries.di.MockAppModule
import br.com.wobbu.restcountries.di.MockUtilsModule
import br.com.wobbu.restcountries.di.viewModel.ViewModelModule

class UiTestApplication : BaseApplication() {
    override val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(MockAppModule(this))
            .utilsModule(MockUtilsModule())
            .build()
    }
}