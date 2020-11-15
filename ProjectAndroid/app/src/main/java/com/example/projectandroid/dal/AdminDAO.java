package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectchucn.domain.Admin;

import java.util.List;

@Dao
public interface AdminDAO {
    @Transaction
    @Insert
    public long insert(Admin admin);

    @Update
    public void update(Admin admin);

    @Delete
    void delete(Admin admin);

    @Query("SELECT * FROM admin")
    public List<Admin> listAdmin();
}
