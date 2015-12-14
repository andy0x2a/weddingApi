package com.newmansoft.model;

/**
 * Created by v-annew on 14-Dec-15.
 */
public class Authorization {


    public Authorization(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;


    public static Authorization SUCCESS = new Authorization("SUCCESS");
    public static Authorization FAIL= new Authorization("FAIL");}
