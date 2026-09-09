package com.xboard.sinhala

import android.content.ClipboardManager
import android.content.Context

class ClipboardHistory(private val context: Context) {

    private val prefs = context.getSharedPreferences("xboard_clipboard", Context.MODE_PRIVATE)
    private val separator = "\u001F"

    fun all(): List<String> {
        val raw = prefs.getString("items", "") ?: return emptyList()
        if (raw.isBlank()) return emptyList()
        return raw.split(separator).filter { it.isNotBlank() }.take(50)
    }

    fun save(text: String) {
        if (text.isBlank()) return
        val updated = buildList {
            add(text)
            addAll(all().filterNot { it == text })
        }.take(50)
        prefs.edit().putString("items", updated.joinToString(separator)).apply()
    }

    fun clear() {
        prefs.edit().remove("items").apply()
    }

    fun systemText(): String? {
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (!cm.hasPrimaryClip()) return null
        return cm.primaryClip?.getItemAt(0)?.coerceToText(context)?.toString()
    }
}
