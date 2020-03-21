package com.example.worldofaits;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class DataModelImg {
    String uri,message,subject,fullName,collegeID,email, time,proImg;

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public DataModelImg(String uri, String message, String subject, String fullName, String collegeID, String email, String time, String proImg) {
        this.uri = uri;
        this.message = message;
        this.subject = subject;
        this.fullName = fullName;
        this.collegeID = collegeID;
        this.email = email;
        this.time = time;
        this.proImg = proImg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataModelImg() {
    }



    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCollegeID() {
        return collegeID;
    }

    public void setCollegeID(String collegeID) {
        this.collegeID = collegeID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getTimestamp() {
//        Calendar cal=Calendar.getInstance(Locale.ENGLISH);
//        cal.setTimeInMillis(Long.parseLong(timestamp));
//        String timestamp = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString();
//        return timestamp;
//    }
//
//    public void setTimestamp(String timestamp) {
//        this.timestamp = timestamp;
//    }
    }