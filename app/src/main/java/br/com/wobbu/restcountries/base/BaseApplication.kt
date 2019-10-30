package br.com.wobbu.restcountries.base

import android.app.Application
import android.content.Context
import br.com.wobbu.restcountries.di.UtilsModule
import br.com.wobbu.restcountries.di.AppComponent
import br.com.wobbu.restcountries.di.AppModule
import br.com.wobbu.restcountries.di.DaggerAppComponent

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    open fun getAppComponent(): AppComponent {
        return DaggerAppComponent.builder().appModule(AppModule(this)).utilsModule(UtilsModule())
            .build()
    }

    protected override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
    }
}