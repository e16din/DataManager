package com.e16din.datamanager

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

const val ID_DEFAULT = "ID_DEFAULT"

object DataManager {

    private const val MESSAGE_NEED_TO_INIT = "Please, initialize DataManager. DataManager.init())"

    val gson = Gson()
        @JvmStatic get

    val boxesMap = HashMap<String, DataBox>()
        @JvmStatic get

    @JvmStatic
    fun getBox(dataBoxId: String): DataBox {
        if (boxesMap.size == 0) {
            throw NullPointerException(MESSAGE_NEED_TO_INIT)
        } // else {

        return if (dataBoxId.isEmpty())
            boxesMap.values.iterator().next()
        else
            boxesMap[dataBoxId]!!
    }

    @JvmStatic
    fun getBox() = getBox(ID_DEFAULT)

    @JvmStatic
    fun init(context: Context) {
        init(PreferenceManager.getDefaultSharedPreferences(context))
    }

    @JvmStatic
    fun init(sharedPreferences: SharedPreferences) {
        boxesMap[ID_DEFAULT] = DataBox(sharedPreferences)
    }

    @JvmStatic
    fun addDataBox(context: Context, id: String) {
        boxesMap[id] = DataBox(context.getSharedPreferences(id, Context.MODE_PRIVATE))
    }

    @JvmStatic
    fun addDataBox(sharedPreferences: SharedPreferences, id: String) {
        boxesMap[id] = DataBox(sharedPreferences)
    }
}
