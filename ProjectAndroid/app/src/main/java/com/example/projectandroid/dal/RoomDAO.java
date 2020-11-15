package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectchucn.domain.Room;

import java.util.List;

@Dao
public interface RoomDAO {
    @Transaction
    @Insert
    public long insert(Room room);

    @Update
    public void update(Room room);

    @Delete
    void delete(Room room);

    @Query("SELECT * FROM room")
    public List<Room> listRoom();
}
