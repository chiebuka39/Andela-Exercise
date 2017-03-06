package com.harrricdev.edwin.andelaexercise.GithubService;

import com.harrricdev.edwin.andelaexercise.Models.AndroidDevelopers;
import com.harrricdev.edwin.andelaexercise.Models.Developer;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by edwin on 3/6/17.
 */

public interface RestApi {


    @GET("search/users?q=language:java+location:lagos")
    Call<AndroidDevelopers> getDevelopers();




}
