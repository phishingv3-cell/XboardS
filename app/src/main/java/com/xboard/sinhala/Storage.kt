package com.xboard.sinhala

import android.content.Context
import org.json.JSONArray

class XBoardStorage(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "xboard_storage",
            Context.MODE_PRIVATE
        )


    fun learn(word: String) {

        if (word.isBlank()) return

        val list =
            learnedWords().toMutableList()

        list.remove(word)

        list.add(0, word)

        prefs.edit()
            .putString(
                "learned",
                JSONArray(
                    list.take(500)
                ).toString()
            )
            .apply()
    }


    fun learnedWords(): List<String> {

        val raw =
            prefs.getString(
                "learned",
                null
            ) ?: return emptyList()

        return try {

            val json =
                JSONArray(raw)

            List(json.length()) {
                json.getString(it)
            }

        } catch (
            _: Exception
        ) {

            emptyList()
        }
    }


    fun saveClipboard(
        text: String
    ) {

        if (text.isBlank()) return

        val list =
            clipboard().toMutableList()

        list.remove(text)

        list.add(0, text)

        prefs.edit()
            .putString(
                "clipboard",
                JSONArray(
                    list.take(50)
                ).toString()
            )
            .apply()
    }


    fun clipboard(): List<String> {

        val raw =
            prefs.getString(
                "clipboard",
                null
            ) ?: return emptyList()

        return try {

            val json =
                JSONArray(raw)

            List(json.length()) {
                json.getString(it)
            }

        } catch (
            _: Exception
        ) {

            emptyList()
        }
    }
}