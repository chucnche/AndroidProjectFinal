package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectchucn.domain.Manager;

import java.util.List;

@Dao
public interface ManagerDAO {
    @Transaction
    @Insert
    public long insert(Manager manager);

    @Update
    public void update(Manager manager);

    @Delete
    void delete(Manager manager);

    @Query("SELECT * FROM manager")
    public List<Manager> listManager();
}
