package com.example.common.RoomSQL;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// Class for define ROOM-SQL saving data
@Entity(tableName = "time_logs")
public class TLog {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "action")
    public String action = "";

    @ColumnInfo(name = "time")
    public String time = "";

    @ColumnInfo(name = "duration")
    public int duration = 0;

    public TLog() { }

    @Ignore
    public TLog(String action, String time, int duration) {
        this.action = action;
        this.time = time;
        this.duration = duration;
    }
}
