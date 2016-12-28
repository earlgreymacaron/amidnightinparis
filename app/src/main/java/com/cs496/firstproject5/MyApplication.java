package com.cs496.firstproject5;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by q on 2016-12-28.
 */

public class MyApplication extends Application {

    public static List<Hotel> hotelList;
    public static List<Hotel> starredList;
    public void onCreate(){
        super.onCreate();
        hotelList = new ArrayList<>();
        starredList = new ArrayList<>();


    }
}
