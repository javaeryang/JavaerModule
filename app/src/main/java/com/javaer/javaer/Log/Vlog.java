package com.javaer.javaer.Log;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/7.
 */

public class Vlog {
    public static final boolean DEBUG = true;

    public static void log(String message){
        if (DEBUG){
            Log.i("javaer", message);
        }
    }

    public static void i(String tag, String message){
        if (DEBUG){
            Log.i(tag, message);
        }
    }
}
