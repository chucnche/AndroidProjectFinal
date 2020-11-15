package com.example.projectandroid.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Manager {
    @PrimaryKey
    @NonNull
    private String maID;
    @ForeignKey(entity = Account.class,
            parentColumns = {"username"},
            childColumns = {"username"},
            onDelete = ForeignKey.CASCADE)
    private String username;
    @ColumnInfo
    private String dob;
    @ColumnInfo
    private int gender;
    @ColumnInfo
    private String mail;
    @ColumnInfo
    private String phoneNumber;
    @ColumnInfo
    private String name;

    public Manager() {
    }

    public Manager(String maID, String username, String dob, int gender, String mail, String phoneNumber, String name) {
        this.maID = maID;
        this.username = username;
        this.dob = dob;
        this.gender = gender;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getMaID() {
        return maID;
    }

    public void setMaID(String maID) {
        this.maID = maID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
