package com.example.islami.darkMode

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)

    fun setDarkModeEnabled(isDarkModeEnabled: Boolean) {
        prefs.edit().putBoolean(Constants.DARK_MODE_KEY, isDarkModeEnabled).apply()
    }

    fun isDarkModeEnabled(): Boolean {
        return prefs.getBoolean(Constants.DARK_MODE_KEY, false)
    }
}