package com.example.projectandroid.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Request {
    @PrimaryKey(autoGenerate = true)
    private int requestID;
    @ForeignKey(entity = Student.class,
            parentColumns = {"stuID"},
            childColumns = {"stuID"},
            onDelete = ForeignKey.CASCADE)
    private String stuID;
    @ColumnInfo
    private String dateRequest;
    @ColumnInfo
    private String requestContent;
    @ColumnInfo
    private String maID;
    @ColumnInfo
    private String dateReply;
    @ColumnInfo
    private String reply;


    public Request()
    {
    }

    public Request(String stuID, String dateRequest, String requestContent, String maID, String dateReply, String reply, int requestID) {
        this.stuID = stuID;
        this.dateRequest = dateRequest;
        this.requestContent = requestContent;
        this.maID = maID;
        this.dateReply = dateReply;
        this.reply = reply;
        this.requestID = requestID;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(String dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getMaID() {
        return maID;
    }

    public void setMaID(String maID) {
        this.maID = maID;
    }

    public String getDateReply() {
        return dateReply;
    }

    public void setDateReply(String dateReply) {
        this.dateReply = dateReply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }
}
