package com.e16din.datamanager

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.Gson

const val ID_DEFAULT = "ID_DEFAULT"

object DataManager {

    private const val MESSAGE_NEED_TO_INIT = "Please, initialize a data box. DataManager.initDefaultDataBox())"

    val gson = Gson()
        @JvmStatic get

    val boxesMap = HashMap<String, DataBox>()
        @JvmStatic get

    @JvmStatic
    fun getBox(dataBoxId: String): DataBox? {
        if (boxesMap.size == 0) {
            Log.w("warning", MESSAGE_NEED_TO_INIT)
            return null
        } // else {

        return if (dataBoxId.isEmpty())
            boxesMap.values.iterator().next()
        else
            boxesMap[dataBoxId]!!
    }

    @JvmStatic
    fun getBox() = getBox(ID_DEFAULT)

    @JvmStatic
    fun initDefaultDataBox(context: Context) =
            initDefaultDataBox(PreferenceManager.getDefaultSharedPreferences(context))

    @JvmStatic
    fun initDefaultDataBox(sharedPreferences: SharedPreferences): DataBox {
        val dataBox = DataBox(sharedPreferences)
        boxesMap[ID_DEFAULT] = dataBox
        return dataBox
    }

    @JvmStatic
    fun addDataBox(context: Context, id: String): DataBox {
        val dataBox = DataBox(context.getSharedPreferences(id, Context.MODE_PRIVATE))
        boxesMap[id] = dataBox
        return dataBox
    }

    @JvmStatic
    fun addDataBox(sharedPreferences: SharedPreferences, id: String): DataBox {
        val dataBox = DataBox(sharedPreferences)
        boxesMap[id] = dataBox
        return dataBox
    }
}
