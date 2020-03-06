package com.example.worldofaits;

import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DataModel {
    String fullName,collegeID, email,password,crtCheck,hosCheck;

    public DataModel(){

    }

    public DataModel(String fullName, String collegeID, String email, String password, String crtCheck, String hosCheck) {
        this.fullName = fullName;
        this.collegeID = collegeID;
        this.email = email;
        this.password = password;
        this.crtCheck = crtCheck;
        this.hosCheck = hosCheck;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCrtCheck() {
        return crtCheck;
    }

    public void setCrtCheck(String crtCheck) {
        this.crtCheck = crtCheck;
    }

    public String getHosCheck() {
        return hosCheck;
    }

    public void setHosCheck(String hosCheck) {
        this.hosCheck = hosCheck;
    }
}
