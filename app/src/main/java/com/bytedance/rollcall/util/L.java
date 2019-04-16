package com.bytedance.rollcall.util;

import android.util.Log;

public class L {
    private static final String TAG = "Logger";
    public static void i(String msg) {
        Log.i(TAG, msg);
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
}