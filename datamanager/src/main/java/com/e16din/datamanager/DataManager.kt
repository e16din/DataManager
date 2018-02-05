package com.e16din.datamanager

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object DataManager {

    val gson = Gson()
    var sharedPreferences: SharedPreferences? = null
        get() {
            try {
                return field
            } catch (e: NullPointerException) {
                throw NullPointerException("Please initialize DataManager. DataManager.init(context))")
            }
        }
    var useCommit = true

    @JvmStatic
    fun init(context: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    @JvmStatic
    operator fun contains(key: String) = sharedPreferences!!.contains(key)

    @JvmStatic
    fun save(key: String, value: Any) {
        val editor = sharedPreferences!!.edit()
        editor.put(key, value)
        apply(editor)
    }

    @JvmStatic
    fun saveAll(data: Iterable<Map.Entry<String, Any>>) {
        val editor = sharedPreferences!!.edit()

        for (`object` in data) {
            val value = `object`.value
            val key = `object`.key

            editor.put(key, value)
        }

        apply(editor)
    }

    @JvmStatic
    fun saveAll(data: Map<String, Any>) {
        saveAll(data.entries)
    }

    private fun apply(editor: SharedPreferences.Editor) {
        if (useCommit) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    @JvmStatic
    fun remove(key: String) {
        val editor = sharedPreferences!!.edit()
        editor.remove(key)
        apply(editor)
    }

    @JvmStatic
    fun clear() {
        val editor = sharedPreferences!!.edit()
        editor.clear()
        apply(editor)
    }

    @JvmStatic
    fun <T : Any?> load(key: String): T? {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null
        } // else {

        return sharedPreferences!!.all[key] as T?
    }

    @JvmStatic
    fun <T : Any?> load(key: String, defaultValue: T): T =
            load(key) as T? ?: defaultValue

    @JvmStatic
    fun <T : Any?> load(key: String, type: Class<T?>): T? {
        if (TextUtils.isEmpty(key) || !contains(key)) {
            return null
        } // else {

        val string = load(key, "")
        return gson.fromJson(string, type)
    }

    private fun SharedPreferences.Editor.put(key: String, value: Any) {
        when (value) {
            is Int -> this.putInt(key, value)
            is Boolean -> this.putBoolean(key, value)
            is String -> this.putString(key, value)
            is Long -> this.putLong(key, value)
            is Float -> this.putFloat(key, value)
            is Set<*> -> this.putStringSet(key, value as Set<String>)
            else -> this.putString(key, gson.toJson(value))
        }
    }

    /**
     * Invisible for java classes
     */
    @JvmStatic
    inline fun <reified T : Any?> loadk(key: String, defaultValue: T? = null): T? = sharedPreferences!![key]
            ?: defaultValue

    inline operator fun <reified T : Any?> SharedPreferences.get(key: String): T? {
        if (TextUtils.isEmpty(key) || !DataManager.contains(key)) {
            return null
        } // else {

        return when (T::class) {
            String::class -> getString(key, "") as T?
            Int::class -> getInt(key, -1) as T?
            Boolean::class -> getBoolean(key, false) as T?
            Float::class -> getFloat(key, -1f) as T?
            Long::class -> getLong(key, -1) as T?
            Set::class -> getStringSet(key, HashSet<String>()) as T?
            else -> {
                val string = getString(key, "")
                gson.fromJson<T>(string, object : TypeToken<T>() {}.type)
            }
        }
    }
}

