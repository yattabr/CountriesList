package br.com.wobbu.restcountries.utils

import br.com.wobbu.restcountries.base.BaseApplication
import br.com.wobbu.restcountries.di.*

class UiTestApplication : BaseApplication() {
    override open val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}