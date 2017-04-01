package com.sunilkumar.android.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sunilkumar on 01-04-2017.
 */

public interface MyApiEndpointInterface {
    @GET("search/users")
    Call<User> getUserNameTom(@Query("q") String name);
}
