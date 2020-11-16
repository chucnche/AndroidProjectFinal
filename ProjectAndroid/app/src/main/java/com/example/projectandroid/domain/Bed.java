package com.example.projectandroid.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Bed {

    @PrimaryKey(autoGenerate = true)
    int bedId;
    @ForeignKey(entity = Room.class,
            parentColumns = {"roomName"},
            childColumns = {"roomName"},
            onDelete = ForeignKey.CASCADE)
    @NonNull
    private String roomName;
    @ColumnInfo
    private int bedNo;
    @ColumnInfo
    private int bedStatus;

    public Bed() {
    }

    public Bed(int bedId, @NonNull String roomName, int bedNo, int bedStatus) {
        this.bedId = bedId;
        this.roomName = roomName;
        this.bedNo = bedNo;
        this.bedStatus = bedStatus;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(@NonNull String roomName) {
        this.roomName = roomName;
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

    @Override
    public String toString() {
        return String.valueOf(bedNo);
    }
}
