package com.hairmunk.app.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferences {
    private const val NAME = "LoginPref"
    private const val MODE = Context.MODE_PRIVATE

    private fun getSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(NAME, MODE)
    }
    fun getStrValue(context: Context?,  KEY: String, defaultValue: String? = null): String? {
        return getSharedPreferences(context!!).getString(KEY, defaultValue)
    }

    fun putStrValue(context: Context?, KEY: String, valueString : String) {
        val editor = getSharedPreferences(context!!).edit()
        editor.putString(KEY, valueString)
        editor.apply()
    }
}