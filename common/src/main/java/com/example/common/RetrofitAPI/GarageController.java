package com.example.common.RetrofitAPI;

import com.example.common.Activity_CarsParent;
import com.example.common.RetrofitAPI.Garage;
import com.example.common.RetrofitAPI.GarageAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GarageController implements Callback<Garage> {

    static final String BASE_URL = "https://pastebin.com/raw/";
    private Garage garage;

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GarageAPI garageAPI = retrofit.create(GarageAPI.class);

        Call<Garage> call = garageAPI.loadGarage();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Garage> call, Response<Garage> response) {
        if (response.isSuccessful()) {
             garage = response.body();
             // send the data and update the labels
             Activity_CarsParent.updateLabels(garage.getName(), garage.getAddress(), garage.getOpen(), garage.getCars());
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<Garage> call, Throwable t) {
        t.printStackTrace();
    }

}
