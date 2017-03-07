package com.harrricdev.edwin.andelaexercise.profiles;

import com.harrricdev.edwin.andelaexercise.Models.Developer;

/**
 * Created by edwin on 3/6/17.
 */

public interface ProfileContract {

    public interface ProfileView{

        void shareDeveloperProfile();

        void showDeveloperProfile();

        void populateViews(Developer dev);

        void showProgress();

        void hideProgress();

    }

    public interface ProfilePresenter{

        void shareProfile();

        void loadProfile();

        void populateViews(String name);

        void onResume(String name);

    }
}
