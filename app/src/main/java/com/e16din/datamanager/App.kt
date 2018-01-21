package com.e16din.datamanager

import android.app.Application

data class User(var name: String)

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initDataManager()

        KEY_TOKEN.save("token")
        KEY_USER.save(User(name = "Alex"))
    }
}
