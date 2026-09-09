package com.xboard.sinhala

import android.content.Context
import android.graphics.Color
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val prefs by lazy { KeyboardPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        val root = page()

        root.addView(text("⌨", 58f, AppTheme.green, Gravity.CENTER))
        root.addView(text("X Board", 36f, Color.WHITE, Gravity.CENTER))
        root.addView(text("Sinhala Keyboard", 22f, AppTheme.green, Gravity.CENTER))
        root.addView(text("Type • Create • Express", 15f, AppTheme.muted, Gravity.CENTER))

        addCard(root, "⌨  Enable Keyboard", "Turn on X Board Sinhala Keyboard in Android settings.") {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }

        addCard(root, "⚙  Keyboard Settings", "Theme, vibration, auto suggestions and input options.") {
            showSettings()
        }

        addCard(root, "❓  Help & Guide", "Learn Sinhala typing, Shift and all toolbar features.") {
            showHelp()
        }

        addCard(root, "ⓘ  About", "X Board Sinhala Keyboard • Android 8.0+ • #07F57E") {
            showAbout()
        }

        val launch = button("Get Started")
        launch.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodPicker()
        }
        root.addView(launch, marginParams())

        setContentView(root)
    }

    private fun showSettings() {
        val root = page()
        root.addView(text("X Board Settings", 28f, Color.WHITE, Gravity.START))

        addSwitch(root, "Vibration", "Small vibration on keypress", prefs.vibrationEnabled) {
            prefs.vibrationEnabled = it
        }

        addSwitch(root, "Auto Correction / Suggestions", "Suggest words while typing", prefs.suggestionsEnabled) {
            prefs.suggestionsEnabled = it
        }

        addCard(root, "🌐  Languages & Input", "Sinhala / English • Android keyboard settings") {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }

        addCard(root, "📋  Clipboard History", "Save and manage copied text") {
            showClipboard()
        }

        addCard(root, "📖  Dictionary", "Sinhala + English keyboard guide") {
            showDictionary()
        }

        addCard(root, "✨  Advanced", "10 English fonts • 20 decoration styles") {
            showStyles()
        }

        addCard(root, "❓  Help", "Usage guide and shortcuts") {
            showHelp()
        }

        root.addView(backButton { showHome() }, marginParams())
        setContentView(root)
    }

    private fun showHelp() {
        val root = page()
        root.addView(text("Help & Guide", 28f, Color.WHITE, Gravity.START))
        HelpData.sections.forEach { (title, description) ->
            addCard(root, title, description) {}
        }
        root.addView(backButton { showHome() }, marginParams())
        setContentView(root)
    }

    private fun showAbout() {
        val root = page()
        root.addView(text("X Board Sinhala Keyboard", 27f, Color.WHITE, Gravity.START))
        addCard(root, "Version", "1.0 • Android 8.0+") {}
        addCard(root, "Brand", "Green accent: #07F57E") {}
        addCard(root, "Core typing", "k → ක් • ka → ක • kA → කැ • Shift → mahaprana/special layer") {}
        addCard(root, "Design", "Dark professional interface based on the supplied X Board preview.") {}
        root.addView(backButton { showHome() }, marginParams())
        setContentView(root)
    }

    private fun showClipboard() {
        val root = page()
        root.addView(text("Clipboard History", 28f, Color.WHITE, Gravity.START))

        val history = ClipboardHistory(this)
        history.systemText()?.let { history.save(it) }

        val items = history.all()

        if (items.isEmpty()) {
            addCard(root, "Empty", "Copied text will appear here.") {}
        } else {
            items.forEachIndexed { index, value ->
                addCard(root, "${index + 1}.", value) {}
            }
        }

        val clear = button("Clear All")
        clear.setOnClickListener {
            history.clear()
            showClipboard()
        }
        root.addView(clear, marginParams())

        root.addView(backButton { showSettings() }, marginParams())
        setContentView(root)
    }

    private fun showDictionary() {
        val root = page()
        root.addView(text("Sinhala Keyboard Guide", 28f, Color.WHITE, Gravity.START))
        SinhalaTransliterator.guide().forEach { (si, code) ->
            addKeyValue(root, si, code)
        }
        root.addView(backButton { showSettings() }, marginParams())
        setContentView(root)
    }

    private fun showStyles() {
        val root = page()
        root.addView(text("10 English Font Styles", 24f, Color.WHITE, Gravity.START))
        UnicodeStyles.all.forEachIndexed { i, style ->
            addKeyValue(root, "${i + 1}. ${style.name}", style.sample)
        }

        root.addView(text("20 Text Decoration Styles", 24f, Color.WHITE, Gravity.START))
        DecorationStyles.all.forEachIndexed { i, style ->
            addKeyValue(root, "${i + 1}. ${style.name}", style.preview)
        }

        root.addView(backButton { showSettings() }, marginParams())
        setContentView(root)
    }

    private fun page(): LinearLayout = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setBackgroundColor(AppTheme.bg)
        setPadding(18, 18, 18, 18)
    }

    private fun addCard(
        root: LinearLayout,
        title: String,
        detail: String,
        action: () -> Unit
    ) {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundResource(R.drawable.card_bg)
            setPadding(15, 13, 15, 13)
            setOnClickListener { action() }
        }
        card.addView(text(title, 17f, Color.WHITE, Gravity.START))
        card.addView(text(detail, 13f, AppTheme.muted, Gravity.START))
        root.addView(card, LinearLayout.LayoutParams(-1, -2).apply {
            setMargins(0, 5, 0, 5)
        })
    }

    private fun addKeyValue(root: LinearLayout, left: String, right: String) {
        val row = LinearLayout(this).apply {
            setBackgroundResource(R.drawable.card_bg)
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(12, 9, 12, 9)
        }
        row.addView(text(left, 16f, AppTheme.green, Gravity.START), LinearLayout.LayoutParams(0, -2, 1f))
        row.addView(text(right, 15f, Color.WHITE, Gravity.START), LinearLayout.LayoutParams(0, -2, 1f))
        root.addView(row, LinearLayout.LayoutParams(-1, -2).apply {
            setMargins(0, 3, 0, 3)
        })
    }

    private fun addSwitch(
        root: LinearLayout,
        title: String,
        detail: String,
        checked: Boolean,
        onChange: (Boolean) -> Unit
    ) {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setBackgroundResource(R.drawable.card_bg)
            setPadding(14, 9, 10, 9)
        }
        val body = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        body.addView(text(title, 17f, Color.WHITE, Gravity.START))
        body.addView(text(detail, 13f, AppTheme.muted, Gravity.START))
        row.addView(body, LinearLayout.LayoutParams(0, -2, 1f))

        val sw = Switch(this).apply {
            isChecked = checked
            setOnCheckedChangeListener { _, value -> onChange(value) }
        }
        row.addView(sw)
        root.addView(row, LinearLayout.LayoutParams(-1, -2).apply {
            setMargins(0, 5, 0, 5)
        })
    }

    private fun text(value: String, size: Float, color: Int, gravity: Int): TextView =
        TextView(this).apply {
            this.text = value
            textSize = size
            setTextColor(color)
            this.gravity = gravity
            setPadding(2, 3, 2, 3)
        }

    private fun button(value: String): Button =
        Button(this).apply {
            text = value
            textSize = 16f
            setTextColor(0xFF062015.toInt())
            setBackgroundResource(R.drawable.green_bg)
        }

    private fun backButton(action: () -> Unit): Button =
        button("Back").apply { setOnClickListener { action() } }

    private fun marginParams() = LinearLayout.LayoutParams(-1, -2).apply {
        setMargins(0, 8, 0, 8)
    }
}
