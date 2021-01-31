package com.example.common.RoomSQL;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.common.RoomSQL.TLog;

import java.util.List;

@Dao
public interface TLogDao {
    @Insert
    void insertAll(TLog...tLogs);

    @Query("SELECT * FROM time_logs")
    List<TLog> getAll();
}
