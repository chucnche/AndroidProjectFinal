package com.example.projectandroid.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class HistoryBook {
    @PrimaryKey(autoGenerate = true)
    private int bookID;
    @ForeignKey(entity = Student.class,
            parentColumns = {"stuID"},
            childColumns = {"stuID"},
            onDelete = ForeignKey.CASCADE)
    private String stuID;
    @ColumnInfo
    private String dateBook;
    @ForeignKey(entity = Room.class,
            parentColumns = {"roomName"},
            childColumns = {"roomName"},
            onDelete = ForeignKey.CASCADE)
    private String roomName;
    @ColumnInfo
    private int bedNo;
    @ColumnInfo
    private int status;
    @ColumnInfo
    private String dateExpiry;

    public HistoryBook()
    {
    }

    public HistoryBook(String stuID, String dateBook, String roomName, int bedNo, int bookID, int status, String dateExpiry) {
        this.stuID = stuID;
        this.dateBook = dateBook;
        this.roomName = roomName;
        this.bedNo = bedNo;
        this.bookID = bookID;
        this.status = status;
        this.dateExpiry = dateExpiry;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getDateBook() {
        return dateBook;
    }

    public void setDateBook(String dateBook) {
        this.dateBook = dateBook;
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

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(String dateExpiry) {
        this.dateExpiry = dateExpiry;
    }
}
