package com.harrricdev.edwin.andelaexercise.profiles;

import com.harrricdev.edwin.andelaexercise.GithubService.ProfileService;
import com.harrricdev.edwin.andelaexercise.Models.Developer;
import com.harrricdev.edwin.andelaexercise.Utils.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by edwin on 3/6/17.
 */

public class ProfilePresenterImpl implements ProfileContract.ProfilePresenter {


    ProfileContract.ProfileView profileView;
    ProfileService profileService;

    public ProfilePresenterImpl(ProfileContract.ProfileView profileView) {
        this.profileView = profileView;
        profileService = ApiUtils.getDeveloper();
    }

    @Override
    public void shareProfile() {
        profileView.shareDeveloperProfile();
    }

    @Override
    public void loadProfile() {
        profileView.showDeveloperProfile();
    }

    @Override
    public void populateViews(String name) {

    }

    @Override
    public void onResume(String name) {
        profileView.showProgress();
        profileService.getDeveloperByName(name).enqueue(new Callback<Developer>() {
            @Override
            public void onResponse(Call<Developer> call, Response<Developer> response) {
                if(response.isSuccessful()){
                    profileView.hideProgress();
                    Developer dev = response.body();
                    profileView.populateViews(dev);
                }
            }

            @Override
            public void onFailure(Call<Developer> call, Throwable t) {

            }
        });
    }
}
