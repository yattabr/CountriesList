package br.com.wobbu.restcountries.base

import android.app.Application
import br.com.wobbu.restcountries.di.AppComponent
import br.com.wobbu.restcountries.di.AppModule
import br.com.wobbu.restcountries.di.DaggerAppComponent
import br.com.wobbu.restcountries.di.UtilsModule

open class BaseApplication : Application() {

    open val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .utilsModule(UtilsModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}