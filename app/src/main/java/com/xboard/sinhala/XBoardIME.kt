package com.xboard.sinhala

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.view.inputmethod.InputMethodManager

class XBoardIME :
    InputMethodService() {

    private lateinit var root: View

    private lateinit var keyArea:
        LinearLayout

    private lateinit var suggestionRow:
        LinearLayout

    private lateinit var storage:
        XBoardStorage

    private lateinit var suggestionEngine:
        SuggestionEngine


    private var englishMode =
        false


    override fun onCreate() {

        super.onCreate()

        storage =
            XBoardStorage(this)

        suggestionEngine =
            SuggestionEngine(
                storage
            )
    }


    override fun onCreateInputView():
        View {

        root =
            layoutInflater.inflate(
                R.layout.keyboard,
                null
            )

        keyArea =
            root.findViewById(
                R.id.keyArea
            )

        suggestionRow =
            root.findViewById(
                R.id.suggestionRow
            )

        setupToolbar()

        buildKeyboard()

        return root
    }


    private fun buildKeyboard() {

        keyArea.removeAllViews()

        KeyboardData.rows
            .forEach { rowData ->

                val row =
                    LinearLayout(this)

                row.orientation =
                    LinearLayout.HORIZONTAL

                row.gravity =
                    Gravity.CENTER

                keyArea.addView(
                    row,
                    LinearLayout.LayoutParams(
                        -1,
                        dp(58)
                    )
                )


                rowData.forEach { data ->

                    row.addView(

                        createKey(data),

                        LinearLayout.LayoutParams(
                            0,
                            dp(54),
                            1f
                        ).apply {

                            setMargins(
                                dp(2),
                                dp(2),
                                dp(2),
                                dp(2)
                            )
                        }
                    )
                }
            }


        addBottomRow()
    }


    private fun createKey(
        data: KeyData
    ): View {

        val frame =
            FrameLayout(this)

        frame.setBackgroundResource(
            R.drawable.key
        )


        frame.setOnTouchListener { _, event ->

            when (
                event.action
            ) {

                MotionEvent.ACTION_DOWN -> {

                    frame.setBackgroundResource(
                        R.drawable.key_pressed
                    )
                }


                MotionEvent.ACTION_UP -> {

                    frame.setBackgroundResource(
                        R.drawable.key
                    )

                    val output =
                        if (englishMode)
                            data.english
                        else
                            data.sinhalaOutput

                    commit(output)

                    updateSuggestions()
                }
            }

            true
        }


        // Sinhala hint TOP RIGHT

        val hint =
            TextView(this)

        hint.text =
            data.sinhalaHint

        hint.textSize =
            10f

        hint.setTextColor(
            Color.rgb(
                7,
                245,
                126
            )
        )

        hint.gravity =
            Gravity.CENTER


        val hintParams =
            FrameLayout.LayoutParams(
                -2,
                dp(22)
            )

        hintParams.gravity =
            Gravity.TOP or Gravity.END

        hintParams.setMargins(
            0,
            dp(2),
            dp(5),
            0
        )


        frame.addView(
            hint,
            hintParams
        )


        // English center

        val letter =
            TextView(this)

        letter.text =
            data.english

        letter.textSize =
            20f

        letter.setTextColor(
            Color.BLACK
        )

        letter.gravity =
            Gravity.CENTER


        frame.addView(
            letter,
            FrameLayout.LayoutParams(
                -1,
                -1
            )
        )


        return frame
    }


    private fun addBottomRow() {

        val row =
            LinearLayout(this)

        row.orientation =
            LinearLayout.HORIZONTAL

        keyArea.addView(
            row,
            LinearLayout.LayoutParams(
                -1,
                dp(58)
            )
        )


        val language =
            bottomButton(
                "සිං / ENG"
            )

        language.setOnClickListener {

            englishMode =
                !englishMode

            buildKeyboard()
        }


        row.addView(
            language,
            weight(1.3f)
        )


        val space =
            bottomButton(
                "SPACE"
            )

        space.setOnClickListener {

            commit(" ")

            updateSuggestions()
        }


        row.addView(
            space,
            weight(2.8f)
        )


        val back =
            bottomButton("⌫")

        back.setOnClickListener {

            currentInputConnection
                ?.deleteSurroundingText(
                    1,
                    0
                )

            updateSuggestions()
        }


        row.addView(
            back,
            weight(1f)
        )


        val enter =
            bottomButton("↵")

        enter.setOnClickListener {

            commit("\n")

            updateSuggestions()
        }


        row.addView(
            enter,
            weight(1f)
        )
    }


    private fun setupToolbar() {

        root.findViewById<TextView>(
            R.id.tLanguage
        ).setOnClickListener {

            englishMode =
                !englishMode

            buildKeyboard()
        }


        root.findViewById<TextView>(
            R.id.tSuggest
        ).setOnClickListener {

            updateSuggestions()
        }


        root.findViewById<TextView>(
            R.id.tEmoji
        ).setOnClickListener {

            showEmoji()
        }


        root.findViewById<TextView>(
            R.id.tDecoration
        ).setOnClickListener {

            showDecorations()
        }


        root.findViewById<TextView>(
            R.id.tClipboard
        ).setOnClickListener {

            showClipboard()
        }


        root.findViewById<TextView>(
            R.id.tFont
        ).setOnClickListener {

            showFonts()
        }


        root.findViewById<TextView>(
            R.id.tDictionary
        ).setOnClickListener {

            showDictionary()
        }
    }


    private fun showEmoji() {

        suggestionRow.removeAllViews()

        XBoardPanels.emojis
            .forEach { emoji ->

                val v =
                    TextView(this)

                v.text =
                    emoji

                v.textSize =
                    25f

                v.gravity =
                    Gravity.CENTER

                v.setPadding(
                    dp(7),
                    0,
                    dp(7),
                    0
                )

                v.setOnClickListener {

                    commit(emoji)
                }

                suggestionRow.addView(v)
            }
    }


    private fun showDecorations() {

        suggestionRow.removeAllViews()

        XBoardPanels.decorations
            .forEach { pattern ->

                val v =
                    TextView(this)

                v.text =
                    pattern.replace(
                        "{text}",
                        "TEXT"
                    )

                v.textSize =
                    14f

                v.setTextColor(
                    Color.BLACK
                )

                v.gravity =
                    Gravity.CENTER

                v.setPadding(
                    dp(12),
                    0,
                    dp(12),
                    0
                )

                v.setOnClickListener {

                    val selected =
                        currentInputConnection
                            ?.getSelectedText(
                                0
                            )
                            ?.toString()
                            ?: ""

                    if (
                        selected.isNotEmpty()
                    ) {

                        commit(
                            pattern.replace(
                                "{text}",
                                selected
                            )
                        )
                    }
                }

                suggestionRow.addView(v)
            }
    }


    private fun showClipboard() {

        suggestionRow.removeAllViews()

        storage
            .clipboard()
            .forEach { item ->

                val v =
                    TextView(this)

                v.text =
                    item

                v.textSize =
                    14f

                v.setTextColor(
                    Color.BLACK
                )

                v.gravity =
                    Gravity.CENTER

                v.setPadding(
                    dp(14),
                    0,
                    dp(14),
                    0
                )

                v.setOnClickListener {

                    commit(item)
                }

                suggestionRow.addView(v)
            }
    }


    private fun showFonts() {

        suggestionRow.removeAllViews()

        XBoardPanels.fonts
            .forEach { font ->

                val v =
                    TextView(this)

                v.text =
                    font

                v.textSize =
                    14f

                v.setTextColor(
                    Color.BLACK
                )

                v.gravity =
                    Gravity.CENTER

                v.setPadding(
                    dp(14),
                    0,
                    dp(14),
                    0
                )

                suggestionRow.addView(v)
            }
    }


    private fun showDictionary() {

        suggestionRow.removeAllViews()

        val words =
            (
                storage.learnedWords()
                + listOf(
                    "ආයුබෝවන්",
                    "ස්තූතියි",
                    "හොඳයි",
                    "කොහොමද",
                    "hello",
                    "thank you",
                    "good",
                    "welcome"
                )
            )
                .distinct()

        words.forEach { word ->

            val v =
                TextView(this)

            v.text =
                "📖 $word"

            v.textSize =
                14f

            v.setTextColor(
                Color.BLACK
            )

            v.gravity =
                Gravity.CENTER

            v.setPadding(
                dp(14),
                0,
                dp(14),
                0
            )

            v.setOnClickListener {

                commit(word)
            }

            suggestionRow.addView(v)
        }
    }


    private fun updateSuggestions() {

        suggestionRow.removeAllViews()

        val before =
            currentInputConnection
                ?.getTextBeforeCursor(
                    100,
                    0
                )
                ?.toString()
                ?: return


        val prefix =
            before
                .split(
                    Regex("\\s+")
                )
                .lastOrNull()
                ?: ""


        if (
            prefix.isBlank()
        ) return


        suggestionEngine
            .suggest(prefix)
            .forEach { word ->

                val v =
                    TextView(this)

                v.text =
                    word

                v.textSize =
                    15f

                v.setTextColor(
                    Color.BLACK
                )

                v.gravity =
                    Gravity.CENTER

                v.setPadding(
                    dp(18),
                    0,
                    dp(18),
                    0
                )


                v.setOnClickListener {

                    replaceCurrentWord(
                        word
                    )
                }


                suggestionRow.addView(v)
            }
    }


    private fun replaceCurrentWord(
        word: String
    ) {

        val connection =
            currentInputConnection
                ?: return


        val before =
            connection
                .getTextBeforeCursor(
                    100,
                    0
                )
                ?.toString()
                ?: return


        val current =
            before
                .split(
                    Regex("\\s+")
                )
                .lastOrNull()
                ?: ""


        connection.deleteSurroundingText(
            current.length,
            0
        )


        connection.commitText(
            word,
            1
        )


        storage.learn(word)
    }


    private fun commit(
        text: String
    ) {

        currentInputConnection
            ?.commitText(
                text,
                1
            )

        if (
            text == " "
        ) {

            val before =
                currentInputConnection
                    ?.getTextBeforeCursor(
                        100,
                        0
                    )
                    ?.toString()


            before
                ?.trim()
                ?.split(
                    Regex("\\s+")
                )
                ?.lastOrNull()
                ?.let {

                    storage.learn(it)
                }
        }
    }


    private fun bottomButton(
        text: String
    ): TextView {

        return TextView(this).apply {

            this.text =
                text

            textSize =
                16f

            setTextColor(
                Color.BLACK
            )

            gravity =
                Gravity.CENTER

            setBackgroundResource(
                R.drawable.key
            )
        }
    }


    private fun weight(
        value: Float
    ): LinearLayout.LayoutParams {

        return LinearLayout.LayoutParams(
            0,
            dp(54),
            value
        ).apply {

            setMargins(
                dp(2),
                dp(2),
                dp(2),
                dp(2)
            )
        }
    }


    private fun dp(
        value: Int
    ): Int {

        return (
            value *
                resources
                    .displayMetrics
                    .density
            ).toInt()
    }
}