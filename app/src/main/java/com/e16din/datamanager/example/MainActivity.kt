package com.e16din.datamanager.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.e16din.datamanager.DataManager
import com.e16din.datamanager.get
import com.e16din.datamanager.put

const val KEY_TOKEN = "Token"
const val KEY_USER = "User"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()
        TestJava.test(DataManager.getBox())
    }

    private fun test() {
        KEY_TOKEN.put("token")
        KEY_USER.put(User(name = "Alex"))

        val token = KEY_TOKEN.get<String>()
        val user = KEY_USER.get<User>()

        Log.i("debug", "Token: $token")
        Log.i("debug", "User: ${user?.name}")
    }
}
