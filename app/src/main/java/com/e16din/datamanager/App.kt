package com.e16din.datamanager

import android.app.Application
import android.support.multidex.MultiDexApplication

data class User(var name: String)

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        initDataManager()
    }
}
