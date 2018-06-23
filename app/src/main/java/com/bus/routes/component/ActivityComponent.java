package com.bus.routes.component;

import com.bus.routes.MainActivity;
import com.bus.routes.module.ActivityModule;
import com.bus.routes.module.PerActivity;
import dagger.Component;

/**
 * Created by Gustavo on 23/06/18.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
