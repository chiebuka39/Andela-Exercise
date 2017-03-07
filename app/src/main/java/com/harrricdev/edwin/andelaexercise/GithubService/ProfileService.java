package com.harrricdev.edwin.andelaexercise.GithubService;

import com.harrricdev.edwin.andelaexercise.Models.Developer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by edwin on 3/7/17.
 */

public interface ProfileService {

    @GET("users/{login}")
    Call<Developer> getDeveloperByName(@Path("login") String login);

}
