package com.example.projectandroid.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AvailibleBookingTime {
    @PrimaryKey
    @NonNull
    private int adID;
    @ColumnInfo
    private String from;
    @ColumnInfo
    private String to;

    public AvailibleBookingTime() {
    }

    public AvailibleBookingTime(int adID, String from, String to) {
        this.adID = adID;
        this.from = from;
        this.to = to;
    }

    public int getAdID() {
        return adID;
    }

    public void setAdID(int adID) {
        this.adID = adID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
