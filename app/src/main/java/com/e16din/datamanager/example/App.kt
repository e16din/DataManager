package com.e16din.datamanager.example

import android.support.multidex.MultiDexApplication
import com.e16din.datamanager.initDataManager

data class User(var name: String)

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        initDataManager()
    }
}
