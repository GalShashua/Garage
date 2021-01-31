package com.example.common;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.common.RetrofitAPI.GarageController;
import com.example.common.RoomSQL.AppDatabase;
import com.example.common.RoomSQL.TLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public abstract class Activity_CarsParent extends AppCompatActivity {
    private LinearLayout cars_LAYOUT_main;
    private TextView cars_LBL_title, duration_LBL;
    private static TextView garage_LBL_name;
    private static TextView garage_LBL_address;
    private static TextView garage_LBL_open;
    private static TextView garage_LBL_cars;
    private long startTimeStamp = 0;
    int durationSum = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        cars_LAYOUT_main = findViewById(R.id.cars_LAYOUT_main);
        cars_LBL_title = findViewById(R.id.app_LBL_title);
        duration_LBL = findViewById(R.id.duration_LBL);
        garage_LBL_name = findViewById(R.id.garage_name_LBL_title);
        garage_LBL_address = findViewById(R.id.garage_address_LBL_title);
        garage_LBL_open = findViewById(R.id.garage_open_LBL_title);
        garage_LBL_cars = findViewById(R.id.garage_cars_LBL_title);
    }

    // add log time to ROOM-SQL
    private void addTlogTime(String action, String time, int duration) {
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TlogsDB.db").build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.tLogDao().insertAll(new TLog(action,time,duration));
            }
        }).start();

    }

    // update labels according to the response from API
    public static void updateLabels(String garage_name, String garage_address, boolean garage_isOpen, List<String> cars) {
        Activity_CarsParent.garage_LBL_name.setText(garage_name);
        Activity_CarsParent.garage_LBL_address.setText(garage_address);
        if(garage_isOpen == true) {
            Activity_CarsParent.garage_LBL_open.setText("Open now!");
        } else {
            Activity_CarsParent.garage_LBL_open.setText("Close now!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<cars.size(); i++) {
            sb.append(cars.get(i) + "\n");
        }
        Activity_CarsParent.garage_LBL_cars.setText(sb);
    }

    // Get the data as json with API
    protected void downloadCars() {
        GarageController garageController = new GarageController();
        garageController.start();
    }

    public void getAllTLogs() {
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TlogsDB.db").build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TLog> tLogs = appDatabase.tLogDao().getAll();
                for(TLog tLog : tLogs) {
                    if(tLog.action.equals("logout onstop")) {
                        Log.i("", ""+tLog.duration);
                        durationSum += tLog.duration;
                        Log.i("duration" , ""+durationSum);
                    }
                }
                duration_LBL.setText("Usage Time in seconds: "+ durationSum);
            }
        }).start();
    }

    public String getCurrentTime() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Israel"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(c.getTime());
    }

    // change UI from another applications
    protected void setTitle(String title) {
        cars_LBL_title.setText(title);
    }

    protected void setBackgroundColor(int color) {
        cars_LAYOUT_main.setBackgroundResource(color);
    }

    // identify login to app
    @Override
    protected void onResume() {
        super.onResume();
        startTimeStamp = System.currentTimeMillis();
        addTlogTime("login on resume", getCurrentTime(), 0);
    }

    // on logout - the duration of usage saved
    @Override
    protected void onStop() {
        super.onStop();
        long duration = System.currentTimeMillis() - startTimeStamp;
        int sec = (int) TimeUnit.MILLISECONDS.toSeconds(duration);
        addTlogTime("logout onstop", getCurrentTime(), (int) sec);
    }
}
