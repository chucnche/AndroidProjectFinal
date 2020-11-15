package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectchucn.domain.HistoryPayment;

import java.util.List;

@Dao
public interface HistoryPaymentDAO {
    @Transaction
    @Insert
    public long insert(HistoryPayment historyPayment);

    @Update
    public void update(HistoryPayment historyPayment);

    @Delete
    void delete(HistoryPayment historyPayment);

    @Query("SELECT * FROM historypayment")
    public List<HistoryPayment> listHistoryPayment();
}
