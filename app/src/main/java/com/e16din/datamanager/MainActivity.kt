package com.e16din.datamanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

const val KEY_TOKEN = "Token"
const val KEY_USER = "User"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = KEY_TOKEN.get<String>()
        val user = KEY_USER.get<User>()

        Log.i("debug", "Token: $token")
        Log.i("debug", "User: ${user?.name}")

        TestJava.test()
    }
}
