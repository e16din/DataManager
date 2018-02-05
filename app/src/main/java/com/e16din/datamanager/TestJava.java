package com.e16din.datamanager;


import android.util.Log;

public class TestJava {

    public static final String KEY_TEST = "Test";

    public static void test() {
        DataManager.save(KEY_TEST, true);
        boolean test = DataManager.load(KEY_TEST, false);

        Log.w("debug", "Test: " + test);
    }
}
