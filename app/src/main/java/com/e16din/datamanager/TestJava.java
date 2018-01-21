package com.e16din.datamanager;


import android.util.Log;

public class TestJava {

    public static final String KEY_TEST = "Test";

    public static void test() {
        DataManager.save(KEY_TEST, true);
        boolean test = (boolean) DataManager.loadValue(KEY_TEST);

        Log.w("debug", "Test: " + test);
    }
}
