package com.e16din.datamanager

import android.app.Application


fun Application.initDataManager() {
    DataManager.init(this)
}

fun gson() = DataManager.gson
fun sharedPreferences() = DataManager.sharedPreferences

// KEY_HELLO_WORLD.save("Hello World")
fun String.save(obj: Any) = DataManager.save(this, obj)

// val str = KEY_HELLO_WORLD.load()
inline fun <reified T : Any?> String.load(defaultValue: T? = null): T? =
        DataManager.loadk(this, defaultValue)

// KEY_HELLO_WORLD.remove()
fun String.remove() = DataManager.remove(this)

// if (KEY_HELLO_WORLD.exist()) { ... }
fun String.exist() = DataManager.contains(this)