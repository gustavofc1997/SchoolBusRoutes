package com.bus.routes.module

import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides

/**
 * Created by Gustavo on 16/06/18.
 */

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }
}
