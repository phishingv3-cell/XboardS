package com.xboard.sinhala

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import org.json.JSONArray

class ClipboardHistory(private val ctx:Context){
 private val pref=ctx.getSharedPreferences("clipboard_history",Context.MODE_PRIVATE)
 private val cm=ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
 private val listener=ClipboardManager.OnPrimaryClipChangedListener{capture()}
 fun start(){cm.addPrimaryClipChangedListener(listener);capture()}
 fun stop(){cm.removePrimaryClipChangedListener(listener)}
 private fun capture(){val c=cm.primaryClip?:return;val s=c.getItemAt(0).coerceToText(ctx).toString().trim();if(s.isEmpty())return;val a=JSONArray(pref.getString("items","[]")?:"[]");val list=mutableListOf<String>();for(i in 0 until a.length())list+=a.getString(i);list.remove(s);list.add(0,s);pref.edit().putString("items",JSONArray(list.take(50)).toString()).apply()}
 fun all():List<String>{val a=JSONArray(pref.getString("items","[]")?:"[]");return List(a.length()){a.getString(it)}}
 fun clear(){pref.edit().remove("items").apply()}
}
