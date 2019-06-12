package com.mobile.hajsownik.activities;

import com.mobile.hajsownik.pojo.Auth;
import com.mobile.hajsownik.pojo.Signup;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("/signup")
    @FormUrlEncoded
    Call<Signup> createUser(@Field("username") String username,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("/signin")
    Call<Auth> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );
}
