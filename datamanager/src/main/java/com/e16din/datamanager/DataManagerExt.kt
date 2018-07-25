package com.e16din.datamanager

import android.app.Application
import java.lang.reflect.Type


fun Application.initDataManager() {
    DataManager.initDefaultDataBox(this)
}

val GSON = DataManager.gson

fun <T> fromJson(json: String, type: Type) = GSON.fromJson<T>(json, type)

fun toJson(obj: Any?) = GSON.toJson(obj)

// KEY_HELLO_WORLD.put("Hello World")
fun String.put(obj: Any?) = DataManager.getBox()?.put(this, obj)

// val str = KEY_HELLO_WORLD.get()
inline fun <reified T : Any?> String.get(defaultValue: T? = null): T? =
        DataManager.getBox()?.getk(this, defaultValue)

// KEY_HELLO_WORLD.remove()
fun String.remove() = DataManager.getBox()?.remove(this)

// if (KEY_HELLO_WORLD.exist()) { ... }
fun String.exist() = DataManager.getBox()?.contains(this)