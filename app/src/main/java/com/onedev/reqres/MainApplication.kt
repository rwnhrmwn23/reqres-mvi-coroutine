package com.onedev.reqres

import android.app.Application
import com.onedev.reqres.di.MainModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(MainModule().module)
        }
    }
}