package com.e16din.datamanager

import android.content.SharedPreferences
import android.text.TextUtils

class DataBox(val sharedPreferences: SharedPreferences) {

    companion object {
        private const val PREFIX_CUSTOM_OBJECT_KEY = "*JO_"
    }

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
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null
        } // else {

        return if (key.startsWith(PREFIX_CUSTOM_OBJECT_KEY))
            getObject(key)
        else
            all[key] as T?
    }

    fun <T : Any?> get(key: String, defaultValue: T): T = get(key) as T? ?: defaultValue

    private fun <T : Any?> getObject(key: String): T? {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null
        } // else {

        val json = get(key, "")
        return fromJson<T>(json)
    }

    private fun SharedPreferences.Editor.put(key: String, value: Any) {
        when (value) {
            is Int -> this.putInt(key, value)
            is Boolean -> this.putBoolean(key, value)
            is String -> this.putString(key, value)
            is Long -> this.putLong(key, value)
            is Float -> this.putFloat(key, value)
            is Set<*> -> this.putStringSet(key, value as Set<String>)
            else -> this.putString("$PREFIX_CUSTOM_OBJECT_KEY$key", toJson(value))
        }
    }
}

