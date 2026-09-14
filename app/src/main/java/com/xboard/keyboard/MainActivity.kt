package com.xboard.keyboard

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Enable Keyboard Settings වෙත යාම
        findViewById<LinearLayout>(R.id.btnEnableKeyboard).setOnClickListener {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }

        // 2. Default Keyboard එක ලෙස Select කිරීමේ Picker එක පෙන්වීම
        findViewById<LinearLayout>(R.id.btnSelectKeyboard).setOnClickListener {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showInputMethodPicker()
        }

        // 3. Test Keyboard (මෙය ඔබගේ මුල් කේතයේ හිස්ව තිබුණි. දැන් මෙයින් Dialog එකක් විවෘත වී කීබෝඩ් එක Test කළ හැක)
        findViewById<LinearLayout>(R.id.btnTestKeyboard).setOnClickListener {
            showTestKeyboardDialog()
        }
    }

    private fun showTestKeyboardDialog() {
        val editText = EditText(this).apply {
            hint = "여기서 키보드를 테스트해보세요 (Type here...)"
            setPadding(40, 40, 40, 40)
        }

        AlertDialog.Builder(this)
            .setTitle("Test X Board")
            .setView(editText)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
