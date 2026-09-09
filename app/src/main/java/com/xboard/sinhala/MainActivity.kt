package com.xboard.sinhala
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity(){
 override fun onCreate(b:Bundle?){super.onCreate(b);setContentView(R.layout.activity_main)
  findViewById<android.widget.Button>(R.id.enable).setOnClickListener{startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))}
  findViewById<android.widget.Button>(R.id.settings).setOnClickListener{startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))}
  findViewById<android.widget.Button>(R.id.help).setOnClickListener{Toast.makeText(this,"Enable X Board, select it from the system keyboard picker, then use the toolbar. Sinhala phonetic input, learned suggestions, emoji, clipboard, 20 fonts, 20 decorations, sound and vibration are included.",Toast.LENGTH_LONG).show()}
  findViewById<android.widget.Button>(R.id.about).setOnClickListener{Toast.makeText(this,"X Board Sinhala Keyboard v1.0 Pro • Android 8.0+",Toast.LENGTH_SHORT).show()}
 }
}
