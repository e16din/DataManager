package com.e16din.datamanager

import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataBox(val sharedPreferences: SharedPreferences) {

    var useCommit = true

    val all: Map<String, *>
        get() = sharedPreferences.all

    operator fun contains(key: String) = sharedPreferences.contains(key)

    fun put(key: String, value: Any) {
        val editor = sharedPreferences.edit()
        editor.put(key, value)
        apply(editor)
    }

    fun putAll(data: Iterable<Map.Entry<String, Any>>) {
        val editor = sharedPreferences.edit()

        for (obj in data) {
            editor.put(obj.key, obj.value)
        }

        apply(editor)
    }

    fun putAll(data: Map<String, Any>) {
        putAll(data.entries)
    }

    private fun apply(editor: SharedPreferences.Editor) {
        if (useCommit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        apply(editor)
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        apply(editor)
    }

    fun <T : Any?> get(key: String): T? {
        if (TextUtils.isEmpty(key)) {
            return null
        } // else {

        return all[key] as T?
    }

    fun <T : Any?> get(key: String, defaultValue: T): T = get(key) as T? ?: defaultValue

    fun <T : Any?> get(key: String, type: Type): T? {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null
        } // else {

        val json = all[key] as String
        return fromJson<T>(json, type)
    }

    fun <T : Any?> get(key: String, type: Type, defaultValue: T): T? {
        return get(key, type) as T? ?: defaultValue
    }

    private fun SharedPreferences.Editor.put(key: String, value: Any) {
        when (value) {
            is Int -> this.putInt(key, value)
            is Boolean -> this.putBoolean(key, value)
            is String -> this.putString(key, value)
            is Long -> this.putLong(key, value)
            is Float -> this.putFloat(key, value)
            is Set<*> -> this.putStringSet(key, value as Set<String>)
            else -> this.putString(key, toJson(value))
        }
    }

    inline fun <reified T : Any?> getk(key: String, defaultValue: T? = null): T? =
            sharedPreferences[key] ?: defaultValue

    inline operator fun <reified T : Any?> SharedPreferences.get(key: String): T? {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null
        } // else {

        return when (T::class) {
            String::class -> getString(key, "") as T?
            Int::class -> getInt(key, 1) as T?
            Boolean::class -> getBoolean(key, false) as T?
            Float::class -> getFloat(key, 1f) as T?
            Long::class -> getLong(key, 1) as T?
            Set::class -> getStringSet(key, HashSet<String>()) as T?
            else -> {
                val string = getString(key, "")
                fromJson<T>(string, object : TypeToken<T>() {}.type)
            }
        }
    }
}

