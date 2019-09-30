package com.csw.android.thespeedreading.util

import android.content.Context
import android.content.SharedPreferences
import com.csw.android.thespeedreading.app.MyApplication

class SpUtil {

    companion object {
        private val spUtilMap = HashMap<String, SpUtil>()

        fun getSpUtil(fileName: String): SpUtil {
            var spUtil = spUtilMap[fileName]
            if (spUtil == null) {
                spUtil = createSpUtil(fileName)
                spUtilMap[fileName] = spUtil
            }
            return spUtil
        }

        private fun createSpUtil(fileName: String): SpUtil {
            return SpUtil(MyApplication.instance, fileName)
        }
    }

    private val sharedPreferences: SharedPreferences

    private constructor(context: Context, fileName: String) {
        sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    fun putInt(k: String, v: Int): SpUtil {
        sharedPreferences.edit().putInt(k, v).apply()
        return this
    }

    fun getInt(k: String, defV: Int = 0): Int {
        return sharedPreferences.getInt(k, defV)
    }

    fun putLong(k: String, v: Long): SpUtil {
        sharedPreferences.edit().putLong(k, v).apply()
        return this
    }

    fun getLong(k: String, defV: Long = 0L): Long {
        return sharedPreferences.getLong(k, defV)
    }

    fun putFloat(k: String, v: Float): SpUtil {
        sharedPreferences.edit().putFloat(k, v).apply()
        return this
    }

    fun getFloat(k: String, defV: Float = 0f): Float {
        return sharedPreferences.getFloat(k, defV)
    }

    fun putBoolean(k: String, v: Boolean): SpUtil {
        sharedPreferences.edit().putBoolean(k, v).apply()
        return this
    }

    fun getBoolean(k: String, defV: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(k, defV)
    }

    fun putString(k: String, v: String): SpUtil {
        sharedPreferences.edit().putString(k, v).apply()
        return this
    }

    fun getString(k: String, defV: String? = null): String? {
        return sharedPreferences.getString(k, defV)
    }
}