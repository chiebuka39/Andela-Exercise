package com.harrricdev.edwin.andelaexercise.DI.Component;

import com.harrricdev.edwin.andelaexercise.DI.Module.AppModule;
import com.harrricdev.edwin.andelaexercise.DI.Module.NetModule;
import com.harrricdev.edwin.andelaexercise.Main.MainFragment;
import com.harrricdev.edwin.andelaexercise.Main.MainPresenterImpl;
import com.harrricdev.edwin.andelaexercise.profiles.ProfilePresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by edwin on 3/6/17.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainFragment mainFragment);
    void inject(MainPresenterImpl mainPresenter);
    void inject(ProfilePresenterImpl profilePresenter);
}
