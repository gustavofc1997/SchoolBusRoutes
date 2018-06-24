package com.bus.routes.module

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides

/**
 * Created by Gustavo on 23/06/18.
 */

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

}
