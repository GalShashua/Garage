package com.example.common.RetrofitAPI;

import com.example.common.RetrofitAPI.Garage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GarageAPI {

    @GET("WypPzJCt")
    Call <Garage> loadGarage();

}
