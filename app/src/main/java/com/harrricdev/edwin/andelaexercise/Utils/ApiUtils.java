package com.harrricdev.edwin.andelaexercise.Utils;

import com.harrricdev.edwin.andelaexercise.GithubService.ProfileService;
import com.harrricdev.edwin.andelaexercise.GithubService.RestApi;
import com.harrricdev.edwin.andelaexercise.GithubService.RetroFitClient;

/**
 * Created by edwin on 3/6/17.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.github.com/";

    public static RestApi getApi() {
        return RetroFitClient.getClient(BASE_URL).create(RestApi.class);
    }

    public static ProfileService getDeveloper() {
        return RetroFitClient.getClient(BASE_URL).create(ProfileService.class);
    }

}
