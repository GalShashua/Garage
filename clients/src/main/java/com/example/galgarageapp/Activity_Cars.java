package com.example.galgarageapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.common.Activity_CarsParent;

public class Activity_Cars extends Activity_CarsParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CLIENTS APP");
        setBackgroundColor(R.color.green_bg);
        downloadCars();
        getAllTLogs();
    }
}