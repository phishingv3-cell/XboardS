package com.xboard.sinhala

import android.app.AlertDialog
import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.text.InputType
import android.view.Gravity
import android.view.HapticFeedbackConstants
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class XBoardImeService : InputMethodService() {

    private enum class Panel {
        NONE, EMOJI, CLIPBOARD, FONTS, DECORATIONS, DICTIONARY, HELP
    }

    private val prefs by lazy { KeyboardPreferences(this) }
    private val clipboard by lazy { ClipboardHistory(this) }

    private val phoneticBuffer = StringBuilder()
    private var shift = false
    private var panel = Panel.NONE
    private var symbols = false

    override fun onCreateInputView(): View = buildKeyboard()

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        phoneticBuffer.clear()
        shift = false
        panel = Panel.NONE
        symbols = false
    }

    private fun buildKeyboard(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(AppTheme.bg)
            setPadding(5.dp(), 4.dp(), 5.dp(), 6.dp())
        }

        if (panel == Panel.NONE) {
            root.addView(buildSuggestions())
            root.addView(buildToolbar())
            root.addView(buildNormalKeys())
        } else {
            root.addView(buildPanelHeader())
            root.addView(buildPanelContent())
        }

        return root
    }

    private fun buildSuggestions(): View {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        if (!prefs.suggestionsEnabled) return row

        SuggestionEngine.suggest(
            phoneticBuffer.toString(),
            prefs.sinhalaMode
        ).forEach { word ->
            row.addView(TextView(this).apply {
                text = word
                textSize = 14f
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
                setPadding(14.dp(), 5.dp(), 14.dp(), 5.dp())
                setOnClickListener { commitSuggestion(word) }
            })
        }
        return row
    }

    private fun buildToolbar(): View {
        val scroll = HorizontalScrollView(this).apply {
            isHorizontalScrollBarEnabled = false
        }
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        toolbar(row, "සිං") { toggleLanguage() }
        toolbar(row, "😊") { openPanel(Panel.EMOJI) }
        toolbar(row, "B") { openPanel(Panel.FONTS) }
        toolbar(row, "📋") { openPanel(Panel.CLIPBOARD) }
        toolbar(row, "✨") { openPanel(Panel.DECORATIONS) }
        toolbar(row, "📖") { openPanel(Panel.DICTIONARY) }
        toolbar(row, "?") { openPanel(Panel.HELP) }
        toolbar(row, "◉") {
            prefs.vibrationEnabled = !prefs.vibrationEnabled
            toast(if (prefs.vibrationEnabled) "Vibration ON" else "Vibration OFF")
        }

        scroll.addView(row)
        return scroll
    }

    private fun toolbar(row: LinearLayout, label: String, action: () -> Unit) {
        row.addView(TextView(this).apply {
            text = label
            textSize = 18f
            gravity = Gravity.CENTER
            setTextColor(if (label == "සිං" || label == "B") AppTheme.green else Color.WHITE)
            setBackgroundResource(R.drawable.key_bg)
            setPadding(14.dp(), 7.dp(), 14.dp(), 7.dp())
            setOnClickListener {
                action()
                vibrate()
            }
        })
    }

    private fun buildNormalKeys(): View {
        val holder = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        if (symbols) {
            listOf(
                "1234567890",
                "!@#$%^&*()",
                "-_=+[]{};:'\",.<>/?",
                "©®™°±×÷∞≤≥"
            ).forEach { line ->
                holder.addView(letterRow(line))
            }
        } else {
            holder.addView(letterRow("qwertyuiop"))
            holder.addView(letterRow("asdfghjkl"))
            holder.addView(thirdRow())
        }

        holder.addView(bottomRow())
        return holder
    }

    private fun letterRow(chars: String): View {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        chars.forEach { c -> row.addView(key(c.toString())) }
        return row
    }

    private fun thirdRow(): View {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        row.addView(key("⇧", 1.4f))
        "zxcvbnm".forEach { c -> row.addView(key(c.toString())) }
        row.addView(key("⌫", 1.4f))
        return row
    }

    private fun bottomRow(): View {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        row.addView(key("?123", 1.15f))
        row.addView(key(if (prefs.sinhalaMode) "සිං" else "EN", 1.05f))
        row.addView(key("SPACE", 3.2f))
        row.addView(key("↵", 1.15f))
        return row
    }

    private fun key(label: String, weight: Float = 1f): TextView {
        return TextView(this).apply {
            text = label
            textSize = if (label.length > 1) 13f else 21f
            gravity = Gravity.CENTER
            setTextColor(if (label == "සිං" || label == "EN") AppTheme.green else Color.WHITE)
            setBackgroundResource(R.drawable.key_bg)
            layoutParams = LinearLayout.LayoutParams(0, 56.dp(), weight).apply {
                setMargins(2.dp(), 2.dp(), 2.dp(), 2.dp())
            }

            setOnClickListener {
                handleKey(label)
                vibrate()
            }

            if (label == "⌫") {
                setOnLongClickListener {
                    selectAllAndClear()
                    vibrate()
                    true
                }
            }
        }
    }

    private fun handleKey(label: String) {
        when (label) {
            "⇧" -> {
                shift = !shift
                refresh()
            }
            "⌫" -> backspace()
            "SPACE" -> space()
            "↵" -> enter()
            "?123" -> {
                symbols = !symbols
                refresh()
            }
            "සිං", "EN" -> toggleLanguage()
            else -> typeKey(label)
        }
    }

    private fun typeKey(label: String) {
        val ic = currentInputConnection ?: return
        val actual = if (shift && label.length == 1) label.uppercase() else label

        if (!prefs.sinhalaMode || symbols) {
            ic.commitText(actual, 1)
            shift = false
            refresh()
            return
        }

        phoneticBuffer.append(actual)

        val result = SinhalaTransliterator.convertWord(
            phoneticBuffer.toString()
        )

        ic.setComposingText(result, 1)

        shift = false
        refresh()
    }

    private fun commitSuggestion(word: String) {
        val ic = currentInputConnection ?: return
        phoneticBuffer.clear()
        ic.finishComposingText()
        ic.commitText(word, 1)
        refresh()
    }

    private fun backspace() {
        val ic = currentInputConnection ?: return

        if (phoneticBuffer.isNotEmpty()) {
            phoneticBuffer.deleteCharAt(phoneticBuffer.lastIndex)
            ic.setComposingText(
                SinhalaTransliterator.convertWord(phoneticBuffer.toString()),
                1
            )
        } else {
            ic.deleteSurroundingText(1, 0)
        }
        refresh()
    }

    private fun space() {
        val ic = currentInputConnection ?: return
        ic.finishComposingText()
        phoneticBuffer.clear()
        ic.commitText(" ", 1)
        refresh()
    }

    private fun enter() {
        val ic = currentInputConnection ?: return
        ic.finishComposingText()
        phoneticBuffer.clear()

        ic.commitText("\n", 1)
        refresh()
    }

    private fun selectAllAndClear() {
        val ic = currentInputConnection ?: return

        try {
            val extracted = ic.getExtractedText(
                ExtractedTextRequest(),
                0
            )
            val text = extracted?.text ?: ""

            if (text.isNotEmpty()) {
                ic.setSelection(0, text.length)
                ic.commitText("", 1)
            } else {
                ic.deleteSurroundingText(10000, 0)
            }
        } catch (_: Throwable) {
            ic.deleteSurroundingText(10000, 0)
        }

        phoneticBuffer.clear()
        ic.finishComposingText()
        refresh()
    }

    private fun toggleLanguage() {
        prefs.sinhalaMode = !prefs.sinhalaMode
        phoneticBuffer.clear()
        currentInputConnection?.finishComposingText()
        refresh()
    }

    private fun openPanel(target: Panel) {
        phoneticBuffer.clear()
        currentInputConnection?.finishComposingText()
        panel = target
        refresh()
    }

    private fun closePanel() {
        panel = Panel.NONE
        refresh()
    }

    private fun buildPanelHeader(): View {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        row.addView(TextView(this).apply {
            text = "‹"
            textSize = 28f
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setPadding(8.dp(), 4.dp(), 8.dp(), 4.dp())
            setOnClickListener { closePanel() }
        })

        row.addView(TextView(this).apply {
            text = when (panel) {
                Panel.EMOJI -> "Emoji"
                Panel.CLIPBOARD -> "Clipboard History"
                Panel.FONTS -> "Font Styles"
                Panel.DECORATIONS -> "Text Decoration"
                Panel.DICTIONARY -> "Sinhala Keyboard Guide"
                Panel.HELP -> "Help"
                Panel.NONE -> ""
            }
            textSize = 18f
            setTextColor(Color.WHITE)
            setPadding(8.dp(), 4.dp(), 8.dp(), 4.dp())
        }, LinearLayout.LayoutParams(0, -2, 1f))

        row.addView(TextView(this).apply {
            text = "X Board"
            textSize = 12f
            setTextColor(AppTheme.green)
        })

        return row
    }

    private fun buildPanelContent(): View {
        return when (panel) {
            Panel.EMOJI -> emojiPanel()
            Panel.CLIPBOARD -> clipboardPanel()
            Panel.FONTS -> fontsPanel()
            Panel.DECORATIONS -> decorationsPanel()
            Panel.DICTIONARY -> dictionaryPanel()
            Panel.HELP -> helpPanel()
            Panel.NONE -> buildNormalKeys()
        }
    }

    private fun emojiPanel(): View {
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        val search = EditText(this).apply {
            hint = "Search emoji..."
            setSingleLine(true)
            inputType = InputType.TYPE_CLASS_TEXT
            setTextColor(Color.WHITE)
            setHintTextColor(AppTheme.muted)
        }
        box.addView(search)

        val grid = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        box.addView(grid)

        val emojis = listOf(
            "😀","😃","😄","😁","😆","😅","😂","🤣",
            "😊","😇","🙂","🙃","😉","😍","🥰","😘",
            "😎","🤔","🤗","😴","😭","😡","🤯","🥳",
            "❤️","🧡","💛","💚","💙","💜","🖤","🤍",
            "👍","👎","👏","🙏","👌","✌️","🤝","💪",
            "🔥","⭐","✨","🎉","🎊","✅","❌","💯",
            "🚀","🎯","💡","📌","🌟","☀️","🌙","🎵"
        )

        fun render(filter: String) {
            grid.removeAllViews()
            val filtered = emojis.filter { filter.isBlank() || it.contains(filter) }
            filtered.chunked(8).forEach { group ->
                val row = LinearLayout(this@XBoardImeService).apply {
                    orientation = LinearLayout.HORIZONTAL
                }
                group.forEach { e ->
                    row.addView(TextView(this@XBoardImeService).apply {
                        text = e
                        textSize = 25f
                        gravity = Gravity.CENTER
                        setPadding(5.dp(), 5.dp(), 5.dp(), 5.dp())
                        setOnClickListener { currentInputConnection?.commitText(e, 1) }
                    })
                }
                grid.addView(row)
            }
        }

        render("")
        search.addTextChangedListener(SimpleTextWatcher { render(it) })

        box.addView(panelBottomBar())
        return box
    }

    private fun clipboardPanel(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        val scroll = ScrollView(this)
        val list = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        clipboard.systemText()?.let { clipboard.save(it) }
        val items = clipboard.all()

        if (items.isEmpty()) {
            list.addView(panelLine("No clipboard history yet."))
        } else {
            items.forEach { item ->
                list.addView(panelLine("›  $item") {
                    currentInputConnection?.commitText(item, 1)
                })
            }
        }

        scroll.addView(list)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))

        val actions = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
        }
        actions.addView(panelButton("Clear All") {
            clipboard.clear()
            refresh()
        }, LinearLayout.LayoutParams(0, -2, 1f))
        actions.addView(panelButton("Back") { closePanel() }, LinearLayout.LayoutParams(0, -2, 1f))
        root.addView(actions)

        return root
    }

    private fun fontsPanel(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        val scroll = ScrollView(this)
        val list = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        UnicodeStyles.all.forEachIndexed { index, style ->
            list.addView(panelLine("${index + 1}.  ${style.name}     ${style.sample}") {
                applySelection(style.transform)
            })
        }

        scroll.addView(list)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        root.addView(panelButton("Back") { closePanel() })
        return root
    }

    private fun decorationsPanel(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        val scroll = ScrollView(this)
        val list = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        DecorationStyles.all.forEachIndexed { index, decoration ->
            list.addView(panelLine("${index + 1}.  ${decoration.name}     ${decoration.preview}") {
                applySelection(decoration.transform)
            })
        }

        scroll.addView(list)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        root.addView(panelButton("Back") { closePanel() })
        return root
    }

    private fun dictionaryPanel(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        val scroll = ScrollView(this)
        val list = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        list.addView(panelLine("Main system:  k → ක්   |   ka → ක   |   kA → කැ"))
        list.addView(panelLine("Vowels: a/A • i/I • u/U • e/E • o/O"))
        list.addView(panelLine("Shift = mahaprana / special-character layer"))

        SinhalaTransliterator.guide().forEach { (si, code) ->
            list.addView(panelLine("$si     ←     $code"))
        }

        scroll.addView(list)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        root.addView(panelButton("Back") { closePanel() })
        return root
    }

    private fun helpPanel(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        val scroll = ScrollView(this)
        val list = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        HelpData.sections.forEach { (title, detail) ->
            list.addView(panelLine("$title\n$detail"))
        }

        scroll.addView(list)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        root.addView(panelButton("Back") { closePanel() })
        return root
    }

    private fun panelLine(text: String, click: (() -> Unit)? = null): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 14f
            setTextColor(Color.WHITE)
            setBackgroundResource(R.drawable.key_bg)
            setPadding(12.dp(), 8.dp(), 12.dp(), 8.dp())
            if (click != null) setOnClickListener { click() }
        }
    }

    private fun panelButton(text: String, action: () -> Unit): TextView {
        return TextView(this).apply {
            this.text = text
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(if (text == "Back") Color.WHITE else Color.BLACK)
            setBackgroundResource(if (text == "Back") R.drawable.key_bg else R.drawable.green_bg)
            setPadding(14.dp(), 9.dp(), 14.dp(), 9.dp())
            setOnClickListener { action(); vibrate() }
        }
    }

    private fun panelBottomBar(): View {
        val row = LinearLayout(this)
        row.addView(panelButton("Back") { closePanel() }, LinearLayout.LayoutParams(-1, -2))
        return row
    }

    private fun applySelection(transform: (String) -> String) {
        val ic = currentInputConnection ?: return
        val selected = ic.getSelectedText(0)?.toString()

        if (selected.isNullOrEmpty()) {
            toast("Select text in the app first")
            return
        }

        ic.commitText(transform(selected), 1)
    }

    private fun refresh() {
        setInputView(buildKeyboard())
    }

    private fun vibrate() {
        if (prefs.vibrationEnabled) {
            getInputView()?.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun Int.dp(): Int =
        (this * resources.displayMetrics.density).toInt()
}
