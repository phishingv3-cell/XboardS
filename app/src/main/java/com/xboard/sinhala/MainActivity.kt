package com.xboard.sinhala

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity :
    AppCompatActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(
            savedInstanceState
        )


        val root =
            LinearLayout(this)

        root.orientation =
            LinearLayout.VERTICAL

        root.gravity =
            Gravity.CENTER

        root.setPadding(
            32,
            32,
            32,
            32
        )


        val title =
            TextView(this)

        title.text =
            "X BOARD"

        title.textSize =
            32f

        title.setTextColor(
            android.graphics.Color.BLACK
        )

        title.gravity =
            Gravity.CENTER

        root.addView(title)


        val line =
            TextView(this)

        line.text =
            "\nSinhala • English\n" +
            "Fast & Simple Keyboard"

        line.textSize =
            17f

        line.gravity =
            Gravity.CENTER

        root.addView(line)


        val enable =
            Button(this)

        enable.text =
            "ENABLE KEYBOARD"

        enable.setOnClickListener {

            startActivity(
                Intent(
                    Settings.ACTION_INPUT_METHOD_SETTINGS
                )
            )
        }

        root.addView(enable)


        val select =
            Button(this)

        select.text =
            "SELECT X BOARD"

        select.setOnClickListener {

            val imm =
                getSystemService(
                    INPUT_METHOD_SERVICE
                ) as android.view.inputmethod.InputMethodManager

            imm.showInputMethodPicker()
        }

        root.addView(select)


        setContentView(root)
    }
}