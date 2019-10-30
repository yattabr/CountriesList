package br.com.wobbu.restcountries.utils

import br.com.wobbu.restcountries.base.BaseApplication
import br.com.wobbu.restcountries.di.AppComponent
import br.com.wobbu.restcountries.di.DaggerAppComponent
import br.com.wobbu.restcountries.di.MockAppModule
import br.com.wobbu.restcountries.di.MockUtilsModule

class UiTestApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun getAppComponent(): AppComponent {
        return DaggerAppComponent.builder().appModule(MockAppModule(this))
            .utilsModule(MockUtilsModule())
            .build()
    }

}