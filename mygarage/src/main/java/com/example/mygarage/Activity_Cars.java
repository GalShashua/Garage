package com.example.mygarage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.common.Activity_CarsParent;

public class Activity_Cars extends Activity_CarsParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("GARAGE APP");
        setBackgroundColor(R.color.teal_700);
        downloadCars();
        getAllTLogs();
    }
}
