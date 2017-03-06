package com.harrricdev.edwin.andelaexercise;

import android.app.Application;

import com.harrricdev.edwin.andelaexercise.DI.Component.DaggerNetComponent;
import com.harrricdev.edwin.andelaexercise.DI.Component.NetComponent;
import com.harrricdev.edwin.andelaexercise.DI.Module.AppModule;
import com.harrricdev.edwin.andelaexercise.DI.Module.NetModule;

/**
 * Created by edwin on 3/6/17.
 */

public class AndelaApplication extends Application {

    private NetComponent mNetComponent;


    private static AndelaApplication instance = new AndelaApplication();

    public static AndelaApplication getInstance() {
        return new AndelaApplication();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        getNetComponent();
    }

    public NetComponent getNetComponent() {
        if (mNetComponent == null){
            mNetComponent = DaggerNetComponent.builder()
                    .appModule(new AppModule(this))
                    .netModule(new NetModule("https://api.github.com/"))
                    .build();
        }
        return mNetComponent;
    }






}
