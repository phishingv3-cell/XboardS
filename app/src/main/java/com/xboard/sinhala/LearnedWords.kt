package com.xboard.sinhala

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class LearnedWords(private val ctx: Context) {
    private val pref = ctx.getSharedPreferences("learned_words", Context.MODE_PRIVATE)

    private data class Item(val key: String, val display: String)

    private fun load(): MutableList<Item> {
        val a = JSONArray(pref.getString("items", "[]") ?: "[]")
        val out = mutableListOf<Item>()
        for (i in 0 until a.length()) {
            val o = a.optJSONObject(i)
            if (o != null) {
                out += Item(o.optString("key"), o.optString("display"))
            } else {
                out += Item(a.optString(i), a.optString(i))
            }
        }
        return out
    }

    fun add(key: String, display: String = key) {
        val k = key.trim()
        val d = display.trim()
        if (k.isEmpty() || d.isEmpty()) return
        val l = load()
        l.removeAll { it.key.equals(k, true) || it.display == d }
        l.add(0, Item(k, d))
        pref.edit().putString(
            "items",
            JSONArray(l.take(500).map { JSONObject().put("key", it.key).put("display", it.display) }).toString()
        ).apply()
    }

    fun suggest(prefix: String, limit: Int = 5): List<String> {
        val p = prefix.lowercase()
        return load().filter { it.key.lowercase().startsWith(p) || it.display.lowercase().startsWith(p) }
            .map { it.display }
            .distinct()
            .take(limit)
    }

    fun all(): List<String> = load().map { it.display }.distinct()

    fun clear() {
        pref.edit().remove("items").apply()
    }
}
