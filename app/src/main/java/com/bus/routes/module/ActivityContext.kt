package com.bus.routes.module

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Qualifier

/**
 * Created by Gustavo on 23/06/18.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityContext
