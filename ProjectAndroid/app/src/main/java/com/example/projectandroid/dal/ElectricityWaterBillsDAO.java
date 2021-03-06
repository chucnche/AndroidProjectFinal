package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectandroid.domain.ElectricityWaterBills;
import com.example.projectandroid.domain.Student;

import java.util.List;

@Dao
public interface ElectricityWaterBillsDAO {
    @Transaction
    @Insert
    public long insert(ElectricityWaterBills electricityWaterBills);

    @Update
    public void update(ElectricityWaterBills electricityWaterBills);

    @Delete
    void delete(ElectricityWaterBills electricityWaterBills);

    @Query("SELECT * FROM electricitywaterbills")
    public List<ElectricityWaterBills> listElectricityWaterBills();
    @Query("SELECT * FROM electricitywaterbills where roomName =:roomname")
    public List<ElectricityWaterBills> getBillByRoomName(String roomname);
}
