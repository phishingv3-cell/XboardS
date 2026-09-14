package com.xboard.keyboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
input android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class XBoardIME : InputMethodService() {
    private var isSinhalaMode = true
    private var isUppercase = false
    private var currentBuffer = StringBuilder()
    private val suggestionList = mutableSetOf("ශුභ දවසක්!", "X Board", "සිංහල", "keyboard")
    private lateinit var suggestionLayout: LinearLayout

    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(R.layout.keyboard_view, null)
        suggestionLayout = view.findViewById(R.id.suggestionLayout)
        setupKeys(view)
        updateSuggestions()
        return view
    }

    private fun setupKeys(view: View) {
        val keyTags = listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p",
            "a", "s", "d", "f", "g", "h", "j", "k", "l",
            "z", "x", "c", "v", "b", "n", "m")

        // Setup character keys
        // (In production layout hierarchy, iterate through rows or find buttons by tag)
        
        view.findViewById<Button>(R.id.btnShift).setOnClickListener {
            isUppercase = !isUppercase
        }

        view.findViewById<Button>(R.id.btnSpace).setOnClickListener {
            commitTypedWord()
            currentInputConnection?.commitText(" ", 1)
        }

        view.findViewById<Button>(R.id.btnEnter).setOnClickListener {
            commitTypedWord()
            currentInputConnection?.sendKeyEvent(
                android.view.KeyEvent(android.view.KeyEvent.ACTION_DOWN, android.view.KeyEvent.KEYCODE_ENTER)
            )
        }

        view.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            val ic = currentInputConnection
            if (currentBuffer.isNotEmpty()) {
                currentBuffer.deleteCharAt(currentBuffer.length - 1)
                ic?.deleteSurroundingText(1, 0)
            } else {
                ic?.deleteSurroundingText(1, 0)
            }
        }

        view.findViewById<Button>(R.id.modeSinhala).setOnClickListener { isSinhalaMode = true }
        view.findViewById<Button>(R.id.modeEnglish).setOnClickListener { isSinhalaMode = false }

        // Clipboard Actions
        view.findViewById<Button>(R.id.btnCopy).setOnClickListener {
            // Implement copy logic via InputConnection selection if available
        }
        view.findViewById<Button>(R.id.btnPaste).setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val pasteData = clipboard.primaryClip?.getItemAt(0)?.text
            if (pasteData != null) {
                currentInputConnection?.commitText(pasteData, 1)
            }
        }
        view.findViewById<Button>(R.id.btnClear).setOnClickListener {
            currentInputConnection?.setSelection(0, 0)
            currentInputConnection?.commitText("", 1)
        }
    }

    private fun handleKeyPress(char: String) {
        val ic = currentInputConnection ?: return
        val targetChar = if (isUppercase) char.uppercase() else char
        
        if (isSinhalaMode) {
            currentBuffer.append(targetChar)
            val translated = SinhalaMapper.translate(currentBuffer.toString())
            if (translated != currentBuffer.toString()) {
                // Replaces the phonetic string with actual Sinhala output glyph
                ic.setComposingText(translated, 1)
            } else {
                ic.commitText(targetChar, 1)
            }
        } else {
            ic.commitText(targetChar, 1)
        }
    }

    private fun commitTypedWord() {
        if (currentBuffer.isNotEmpty()) {
            suggestionList.add(currentBuffer.toString())
            currentBuffer.clear()
            updateSuggestions()
        }
    }

    private fun updateSuggestions() {
        suggestionLayout.removeAllViews()
        for (word in suggestionList) {
            val tv = TextView(this).apply {
                text = word
                setTextColor(android.graphics.Color.parseColor("#07f57e"))
                textSize = 14f
                setPadding(16, 0, 16, 0)
                setOnClickListener {
                    currentInputConnection?.commitText(word, 1)
                }
            }
            suggestionLayout.addView(tv)
        }
    }
}
