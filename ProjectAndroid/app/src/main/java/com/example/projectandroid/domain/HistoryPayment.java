package com.example.projectandroid.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class HistoryPayment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = Student.class,
            parentColumns = {"stuID"},
            childColumns = {"stuID"},
            onDelete = ForeignKey.CASCADE)
    private String stuID;
    @ColumnInfo
    private String datePay;
    @ColumnInfo
    private String type;
    @ColumnInfo
    private int moneyPay;
    @ForeignKey(entity = Room.class,
            parentColumns = {"roomName"},
            childColumns = {"roomName"},
            onDelete = ForeignKey.CASCADE)
    private String roomName;

    public HistoryPayment()
    {
    }

    public HistoryPayment(int id, String stuID, String datePay, String type, int moneyPay, String roomName) {
        this.id = id;
        this.stuID = stuID;
        this.datePay = datePay;
        this.type = type;
        this.moneyPay = moneyPay;
        this.roomName = roomName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getDatePay() {
        return datePay;
    }

    public void setDatePay(String datePay) {
        this.datePay = datePay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMoneyPay() {
        return moneyPay;
    }

    public void setMoneyPay(int moneyPay) {
        this.moneyPay = moneyPay;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
