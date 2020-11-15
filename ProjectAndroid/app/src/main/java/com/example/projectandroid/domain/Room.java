package com.example.projectandroid.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Room {
    @PrimaryKey
    @NonNull
    private String roomName;
    @ColumnInfo
    private int gender;
    @ColumnInfo
    private int bedNo;
    @ColumnInfo
    private int bedStatus;
    @ColumnInfo
    private int price;
    public Room()
    {
    }

    public Room(String roomName, int gender, int bedNo, int bedStatus, int price) {
        this.roomName = roomName;
        this.gender = gender;
        this.bedNo = bedNo;
        this.bedStatus = bedStatus;
        this.price = price;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getBedNo() {
        return bedNo;
    }

    public void setBedNo(int bedNo) {
        this.bedNo = bedNo;
    }

    public int getBedStatus() {
        return bedStatus;
    }

    public void setBedStatus(int bedStatus) {
        this.bedStatus = bedStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
