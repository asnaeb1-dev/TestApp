package com.abhigyan.user.testapp;

import android.content.Context;
import android.content.SharedPreferences;

public class FilterOptionsClass {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public FilterOptionsClass(Context context) {
        Context context1 = context;
        initializeSharedPreference(context);
    }

    public  void initializeSharedPreference(Context context)
    {//instantiate the shared preference
        sharedPreferences = context.getSharedPreferences("APPLICATION_SETTINGS", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    private void applyChanges()
    {// apply changed made to the settings
        editor.apply();
    }

    public int getSortOptions() {
        return sharedPreferences.getInt("sort_options", 0);
    }

    public void setSortOptions(int sortOptions) {
        editor.putInt("sort_options", sortOptions);
        applyChanges();
    }

    public int getOrderOptions() {
        //0 - descending 1 - ascending
        return sharedPreferences.getInt("order_options", 0);
    }

    public void setOrderOptions(int orderOptions) {
        editor.putInt("order_options", orderOptions);
        applyChanges();
    }
}

/*
    sort options:
    1) activity - 0
    2) votes- 1
    3) hot - 2

 */