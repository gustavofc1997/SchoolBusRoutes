package com.bus.routes.application

import android.app.Application
import android.content.Context
import com.bus.routes.component.ApplicationComponent
import com.bus.routes.component.DaggerApplicationComponent
import com.bus.routes.module.ApplicationModule

class RoutesApplication:Application() {


    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var appComponent: ApplicationComponent


        operator fun get(context: Context): RoutesApplication {
            return context.applicationContext as RoutesApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        appComponent.inject(this)

    }
}