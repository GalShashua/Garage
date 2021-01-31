package com.example.common.RoomSQL;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.common.RoomSQL.TLog;
import com.example.common.RoomSQL.TLogDao;

@Database(entities = {TLog.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TLogDao tLogDao();
}