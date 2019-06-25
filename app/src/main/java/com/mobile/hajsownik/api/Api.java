package com.mobile.hajsownik.api;

import com.mobile.hajsownik.pojo.DaneLog;
import com.mobile.hajsownik.pojo.Auth;
import com.mobile.hajsownik.pojo.Lista;
import com.mobile.hajsownik.pojo.Signup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {

    @Headers("Content-Type: application/json")
    @POST("/signup")
    Call<Signup> createUser(@Body DaneLog daneLog);

    @Headers("Content-Type: application/json")
    @POST("/signin")
    Call<Auth> loginUser(@Body DaneLog daneLog);

    @Headers("Content-Type: application/json")
    @GET("/credits/sum")
    Call<Float> sumaUzn(@Header("Authorization")String auth);

    @Headers("Content-Type: application/json")
    @GET("/liabilities/sum")
    Call<Float> sumaZad(@Header("Authorization")String auth);

    @Headers("Content-Type: application/json")
    @GET("/liabilitiesstack")
    Call<List<Lista>> sumaLista(@Header("Authorization")String auth);

    @Headers("Content-Type: application/json")
    @GET("/creditsstack")
    Call<List<Lista>> kredytLista(@Header("Authorization")String auth);
}
