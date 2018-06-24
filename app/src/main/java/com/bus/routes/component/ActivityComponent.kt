package com.bus.routes.component

import com.bus.routes.showroutes.RoutesActivity
import com.bus.routes.module.ActivityModule
import com.bus.routes.module.PerActivity
import dagger.Component

/**
 * Created by Gustavo on 23/06/18.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(routesActivity: RoutesActivity)

}
