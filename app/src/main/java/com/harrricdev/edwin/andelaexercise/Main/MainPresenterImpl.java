package com.harrricdev.edwin.andelaexercise.Main;

import android.util.Log;

import com.harrricdev.edwin.andelaexercise.AndelaApplication;
import com.harrricdev.edwin.andelaexercise.GithubService.RestApi;
import com.harrricdev.edwin.andelaexercise.Models.AndroidDevelopers;
import com.harrricdev.edwin.andelaexercise.Models.Item;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by edwin on 3/6/17.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {

    @Inject
    Retrofit retrofit;

    MainContract.MainView mMainView;
    RestApi restApi;

    public MainPresenterImpl(MainContract.MainView mainView) {
        mMainView = mainView;

        AndelaApplication.getInstance().getNetComponent().inject(this);
        restApi = getService();
        mMainView.setBasePresenter(this);
    }


    @Override
    public void loadDevelopers() {
        restApi.getDevelopers().enqueue(new Callback<AndroidDevelopers>() {
            @Override
            public void onResponse(Call<AndroidDevelopers> call, Response<AndroidDevelopers> response) {
                if(response.isSuccessful()) {
                    mMainView.showDevelopers(response.body().getItems());
                    //mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");

                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<AndroidDevelopers> call, Throwable t) {
                //showErrorMessage();
                Log.d("MainActivity", "error loading from API");
            }
        });

    }

    public  RestApi getService() {
        return retrofit.create(RestApi.class);
    }
}
