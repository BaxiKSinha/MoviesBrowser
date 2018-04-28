package com.baxi.moviesbrowser;

import android.app.Application;

import com.baxi.moviesbrowser.di.components.ApplicationComponent;
import com.baxi.moviesbrowser.di.components.DaggerApplicationComponent;
import com.baxi.moviesbrowser.di.modules.ApplicationModule;

/**
 * Created by Baxi on 2018/04/25.
 *
 */

public class MyApplication extends Application {

    private static ApplicationComponent applicationComponent;

    public MyApplication(){

    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

}

