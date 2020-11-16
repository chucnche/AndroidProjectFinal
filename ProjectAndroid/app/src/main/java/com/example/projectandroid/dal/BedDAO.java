package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectandroid.domain.Bed;

import java.util.List;

@Dao
public interface BedDAO {
    @Transaction
    @Insert
    public long insert(Bed bed);

    @Update
    public void update(Bed bed);

    @Delete
    void delete(Bed bed);

    @Query("SELECT * FROM bed")
    public List<Bed> listBed();

    @Query("SELECT * FROM bed WHERE roomName =:roomName AND bedStatus=0")
    public List<Bed> listBedByRoomName(String roomName);

    @Query("SELECT * FROM bed WHERE roomName =:roomName AND bedNo =:bed")
    public Bed listBedByRoomNameBed(String roomName,int bed);
}
