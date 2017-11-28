package com.e16din.datamanager

import android.annotation.TargetApi
import android.os.Build
import java.lang.reflect.Type


fun gson() = DataManager.getGson()
fun sharedPreferences() = DataManager.getSharedPreferences()

// KEY_HELLO_WORLD.save("Hello World")

fun String.save(obj: Any) = DataManager.saveObj(this, obj)

// val str = KEY_HELLO_WORLD.loadString()

fun <T> String.load(type: Type): T? = DataManager.load(this, type)
fun String.loadString() = DataManager.loadString(this)
fun String.loadBool() = DataManager.loadBool(this)
fun String.loadInt(defaultValue: Int = -1) = DataManager.loadInt(this, defaultValue)
fun String.loadLong(defaultValue: Long = -1L) = DataManager.loadLong(this, defaultValue)
fun String.loadFloat(defaultValue: Float) = DataManager.loadFloat(this, defaultValue)
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
fun String.loadStringSet() = DataManager.loadStringSet(this)

// KEY_HELLO_WORLD.remove()

fun String.remove() = DataManager.remove(this)

// KEY_HELLO_WORLD.contains()

fun String.contains() = DataManager.contains(this)