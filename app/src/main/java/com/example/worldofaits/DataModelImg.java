package com.example.worldofaits;

public class DataModelImg {
    String uri,message,subject,fullName,collegeID,email;
    public DataModelImg() {
    }

    public DataModelImg(String message, String subject, String fullName, String collegeID, String email) {
        this.message = message;
        this.subject = subject;
        this.fullName = fullName;
        this.collegeID = collegeID;
        this.email = email;
    }

    public DataModelImg(String uri, String message, String subject, String fullName, String collegeID, String email) {
        this.uri = uri;
        this.message = message;
        this.subject = subject;
        this.fullName = fullName;
        this.collegeID = collegeID;
        this.email = email;
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
}
