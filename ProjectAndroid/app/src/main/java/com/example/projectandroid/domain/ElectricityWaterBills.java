package com.example.projectandroid.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class ElectricityWaterBills {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = Room.class,
            parentColumns = {"roomName"},
            childColumns = {"roomName"},
            onDelete = ForeignKey.CASCADE)
    private String roomName;
    @ColumnInfo
    private String monthYear;
    @ColumnInfo
    private int soDien;
    @ColumnInfo
    private int soNuoc;
    @ColumnInfo
    private int paid;

    public ElectricityWaterBills() {
    }

    public ElectricityWaterBills(int id, String roomName, String monthYear, int soDien, int soNuoc, int paid) {
        this.id = id;
        this.roomName = roomName;
        this.monthYear = monthYear;
        this.soDien = soDien;
        this.soNuoc = soNuoc;
        this.paid = paid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public int getSoDien() {
        return soDien;
    }

    public void setSoDien(int soDien) {
        this.soDien = soDien;
    }

    public int getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        this.soNuoc = soNuoc;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
