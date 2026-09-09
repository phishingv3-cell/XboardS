package com.xboard.sinhala

import android.content.Context

class KeyboardPreferences(context: Context) {
    private val prefs = context.getSharedPreferences("xboard_prefs", Context.MODE_PRIVATE)

    var sinhalaMode: Boolean
        get() = prefs.getBoolean("sinhala_mode", true)
        set(value) = prefs.edit().putBoolean("sinhala_mode", value).apply()

    var vibrationEnabled: Boolean
        get() = prefs.getBoolean("vibration", true)
        set(value) = prefs.edit().putBoolean("vibration", value).apply()

    var suggestionsEnabled: Boolean
        get() = prefs.getBoolean("suggestions", true)
        set(value) = prefs.edit().putBoolean("suggestions", value).apply()

    var fontStyle: Int
        get() = prefs.getInt("font_style", 0)
        set(value) = prefs.edit().putInt("font_style", value).apply()

    var decorationStyle: Int
        get() = prefs.getInt("decoration_style", 0)
        set(value) = prefs.edit().putInt("decoration_style", value).apply()
}
