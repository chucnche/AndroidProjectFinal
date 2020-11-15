package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectchucn.domain.Request;

import java.util.List;

@Dao
public interface RequestDAO {
    @Transaction
    @Insert
    public long insert(Request request);

    @Update
    public void update(Request request);

    @Delete
    void delete(Request request);

    @Query("SELECT * FROM request")
    public List<Request> listRequest();
}
