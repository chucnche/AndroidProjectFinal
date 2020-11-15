package com.example.projectandroid.dal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.projectandroid.domain.Account;

import java.util.List;

@Dao
public interface AccountDAO {
    @Transaction
    @Insert
    public long insert(Account account);

    @Update
    public void update(Account account);

    @Delete
    void delete(Account account);

    @Query("SELECT * FROM account")
    public List<Account> listAccount();
    @Query("SELECT * FROM account where username =:username AND password =:password")
    public Account getAccount(String username, String password);
}
