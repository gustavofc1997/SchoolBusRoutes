package com.bus.routes.component;

import android.app.Application;
import android.content.Context;

import com.bus.routes.application.RoutesApplication;
import com.bus.routes.module.ApplicationContext;
import com.bus.routes.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by Gustavo on 23/06/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(RoutesApplication demoApplication);

    @ApplicationContext
    Context getContext();

    Application getApplication();


}
