package com.abhigyan.user.testapp;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Config {

    public static final String CLIENT_ID = "14814";
    public static final String API_KEY = "QlfyEJsACPXOXcK9tyxTeg((";
    public static final String CLIENT_SECRET_KEY = "tH6M4kS4bzUjMRnQ7saeLw((";
    public static final String MAIN_URL = "https://api.stackexchange.com";

    /*
    Example question id - 19365391
     */

    public String reformatDate(long unixSeconds)
    {
        Date date = new java.util.Date(unixSeconds*1000L);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }
}
