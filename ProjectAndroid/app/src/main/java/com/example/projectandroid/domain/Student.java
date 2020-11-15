package com.example.projectandroid.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey
    @NonNull
    private String stuID;
    @ForeignKey(entity = Account.class,
            parentColumns = {"username"},
            childColumns = {"username"},
            onDelete = ForeignKey.CASCADE)
    private String username;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String dob;
    @ColumnInfo
    private int gender;
    @ColumnInfo
    private String roomName;
    @ColumnInfo
    private int bedNo;
    @ColumnInfo
    private String bookingDate;
    @ColumnInfo
    private int moneyAccount;
    @ColumnInfo
    private int debt;

    public Student()
    {
    }

    public Student(String stuID, String username, String name, String dob, int gender, String roomName, int bedNo, String bookingDate, int moneyAccount, int debt) {
        this.stuID = stuID;
        this.username = username;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.roomName = roomName;
        this.bedNo = bedNo;
        this.bookingDate = bookingDate;
        this.moneyAccount = moneyAccount;
        this.debt = debt;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getBedNo() {
        return bedNo;
    }

    public void setBedNo(int bedNo) {
        this.bedNo = bedNo;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getMoneyAccount() {
        return moneyAccount;
    }

    public void setMoneyAccount(int moneyAccount) {
        this.moneyAccount = moneyAccount;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
}
