package com.mk8.pruebaloycus

import androidx.multidex.MultiDexApplication
import com.mk8.pruebaloycus.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(InjectorModule.provideModules())
        }
    }
}