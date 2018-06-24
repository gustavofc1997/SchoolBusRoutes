package com.bus.routes.component

import android.app.Application
import android.content.Context

import com.bus.routes.application.RoutesApplication
import com.bus.routes.module.ApplicationContext
import com.bus.routes.module.ApplicationModule
import com.bus.routes.module.NetworkModule

import javax.inject.Singleton

import dagger.Component


/**
 * Created by Gustavo on 23/06/18.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    @get:ApplicationContext
    val context: Context

    val application: Application

    val netModule: NetworkModule

    fun inject(demoApplication: RoutesApplication)

}
