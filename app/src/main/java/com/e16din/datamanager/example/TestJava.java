package com.e16din.datamanager.example;


import android.util.Log;

import com.e16din.datamanager.DataBox;

import static com.e16din.datamanager.DataManagerExtKt.toJson;

public class TestJava {

    public static final String KEY_TEST = "Test";

    public static void test(DataBox dataBox) {
        dataBox.put(KEY_TEST, new TestObject());

        TestObject test = dataBox.get(KEY_TEST, TestObject.class);

        Log.i("debug", "Test: " + toJson(test));
    }
}
