package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectchucn.domain.AvailibleBookingTime;

import java.util.List;

@Dao
public interface AvailibleBookingTimeDAO {
    @Transaction
    @Insert
    public long insert(AvailibleBookingTime availibleBookingTime);

    @Update
    public void update(AvailibleBookingTime availibleBookingTime);

    @Delete
    void delete(AvailibleBookingTime availibleBookingTime);

    @Query("SELECT * FROM availiblebookingtime")
    public List<AvailibleBookingTime> listAvailibleBookingTime();
}
