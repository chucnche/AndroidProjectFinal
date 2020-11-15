package com.example.projectandroid.domain;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Admin {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = Account.class,
            parentColumns = {"username"},
            childColumns = {"username"},
            onDelete = ForeignKey.CASCADE)
    private String username;

    public Admin() {
    }

    public Admin(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
