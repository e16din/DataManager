package com.e16din.datamanager;


import android.util.Log;

public class TestJava {

    public static final String KEY_TEST = "Test";

    public static void test() {
        DataBox dataBox = DataManager.getBox();

        dataBox.put(KEY_TEST, true);

        boolean test = dataBox.get(KEY_TEST, false);

        Log.w("debug", "Test: " + test);
    }
}
