package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectandroid.domain.ElectricityWaterBills;
import com.example.projectandroid.domain.HistoryBook;

import java.util.List;

@Dao
public interface HistoryBookDAO {
    @Transaction
    @Insert
    public long insert(HistoryBook historyBook);

    @Update
    public void update(HistoryBook historyBook);

    @Delete
    void delete(HistoryBook historyBook);

    @Query("SELECT * FROM historybook")
    public List<HistoryBook> listHistoryBook();
    
    @Query("SELECT * FROM historybook WHERE roomName = :roomName AND bedNo = :bedNo AND status = 1")
    public List<HistoryBook> listPendingHistoryBookRoomNameBedNo(String roomName, int bedNo);

    @Query("SELECT * FROM historybook WHERE stuID =:stuID")
    public List<HistoryBook> listHistoryBookByUsername(String stuID);


}
