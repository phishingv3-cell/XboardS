package com.xboard.sinhala

import android.graphics.Color
import android.graphics.Typeface
import android.inputmethodservice.InputMethodService
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.InputType
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*

class XBoardInputMethodService:InputMethodService(){
 private lateinit var root:LinearLayout; private lateinit var sug:LinearLayout
 private var english=false; private var shift=false; private var panel=""; private var font=0; private var deco=0; private var sound=true; private var vibration=true; private var composing=""; private val learned by lazy{LearnedWords(this)}; private val clips by lazy{ClipboardHistory(this)}
 private val green=Color.rgb(7,245,126); private val bg=Color.rgb(2,10,15); private val key=Color.rgb(9,25,35)
 override fun onCreateInputView():View{clips.start();build();return root}
 override fun onDestroy(){clips.stop();super.onDestroy()}
 private fun build(){root=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setBackgroundColor(bg);setPadding(dp(6),dp(5),dp(6),dp(3))};sug=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL};root.addView(sug,LinearLayout.LayoutParams(-1,dp(38)));toolbar();refreshSuggestions();content()}
 private fun toolbar(){val r=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL}; tool(r,"සිං↔EN"){english=!english;shift=false;clearCompose();refresh()};tool(r,"😀"){panel="emoji";refresh()};tool(r,"📋"){panel="clip";refresh()};tool(r,"B"){panel="font";refresh()};tool(r,"✨"){panel="deco";refresh()};tool(r,if(sound)"🔊" else "🔇"){sound=!sound;refresh()};tool(r,if(vibration)"📳" else "📴"){vibration=!vibration;refresh()};tool(r,"?"){panel="help";refresh()};root.addView(r,LinearLayout.LayoutParams(-1,dp(42)))}
 private fun refresh(){while(root.childCount>2)root.removeViewAt(2);refreshSuggestions();content()}
 private fun clearCompose(){composing="";currentInputConnection?.finishComposingText()}
 private fun refreshSuggestions(){sug.removeAllViews();val prefix=composing.lowercase();val learnedList=learned.suggest(prefix);val fallback=if(english)listOf("the","you","and","what","are") else listOf("මෙය","මම","මට","ඔබ","හරි");(learnedList+fallback).distinct().take(5).forEachIndexed{i,t->val b=btn(t,if(i==0)green else key,if(i==0)bg else Color.WHITE){commitWord(t)};sug.addView(b,LinearLayout.LayoutParams(0,dp(34),1f).apply{setMargins(dp(2),0,dp(2),0)})}}
 private fun content(){when(panel){"emoji"->emoji();"clip"->clips();"font"->fonts();"deco"->decos();"help"->help();else->keys()}}
 private fun keys(){
  val rows=if(english){if(shift)listOf("QWERTYUIOP","ASDFGHJKL","ZXCVBNM") else listOf("qwertyuiop","asdfghjkl","zxcvbnm")}else listOf("qwertyuiop","asdfghjkl","zxcvbnm")
  rows.forEachIndexed{idx,row->{val r=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL}
   if(idx==2){val sh=btn("⇧",if(english&&shift)green else key,if(english&&shift)bg else Color.WHITE){if(english){buzz();shift=!shift;refresh()}};r.addView(sh,LinearLayout.LayoutParams(0,dp(44),.85f).apply{setMargins(dp(2),dp(2),dp(2),dp(2))})}
   row.forEach{ch->key(r,ch.toString())}
   if(idx==2){key(r,"⌫",.85f){buzz();currentInputConnection?.deleteSurroundingText(1,0);if(composing.isNotEmpty())composing=composing.dropLast(1);refreshSuggestions()}}
   root.addView(r,LinearLayout.LayoutParams(-1,dp(48)))}}
  val r=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL}
  key(r,"?123",1f){commitText("?123")};key(r,"සිං",.8f){english=!english;shift=false;clearCompose();refresh()};key(r,"SPACE",2.2f){finishWord(" ")};key(r,"↵",.9f){finishWord("")}
  root.addView(r,LinearLayout.LayoutParams(-1,dp(50)))
 }
 private fun key(r:LinearLayout,t:String,w:Float=1f,a:(()->Unit)?=null){
  val b=if(!english && t.length==1 && t[0].isLetter()) sinhalaKey(t){click(t,a)} else btn(t,key,Color.WHITE){click(t,a)}
  r.addView(b,LinearLayout.LayoutParams(0,dp(44),w).apply{setMargins(dp(2),dp(2),dp(2),dp(2))})
 }
 private fun sinhalaKey(t:String,a:()->Unit):View {
  val frame=FrameLayout(this).apply{setBackgroundResource(R.drawable.key_bg);setOnClickListener{a()}}
  val main=TextView(this).apply{text=t;setTextColor(Color.WHITE);textSize=13f;gravity=Gravity.CENTER;setTypeface(Typeface.DEFAULT,Typeface.BOLD)}
  val si=TextView(this).apply{text=sinhalaHint(t);setTextColor(green);textSize=8f;gravity=Gravity.CENTER;setTypeface(Typeface.DEFAULT,Typeface.BOLD);setPadding(0,0,2,0)}
  frame.addView(main,FrameLayout.LayoutParams(-1,-1))
  frame.addView(si,FrameLayout.LayoutParams(dp(20),dp(15),Gravity.TOP or Gravity.RIGHT))
  return frame
 }
 private fun sinhalaHint(t:String):String=when(t.lowercase()){
  "q"->"ඤ";"w"->"ව";"e"->"එ";"r"->"ර";"t"->"ත";"y"->"ය";"u"->"උ";"i"->"ඉ";"o"->"ඔ";"p"->"ප";
  "a"->"අ";"s"->"ස";"d"->"ද";"f"->"ෆ";"g"->"ග";"h"->"හ";"j"->"ජ";"k"->"ක";"l"->"ල";
  "z"->"ශ";"x"->"ඔ";"c"->"ච";"v"->"ව";"b"->"බ";"n"->"න";"m"->"ම";else->""
 }
 private fun click(t:String,a:(()->Unit)?){buzz();if(t.length==1&&t[0].isLetter()){if(english)commitText(if(shift)t.uppercase() else t) else{composing+=t;currentInputConnection?.setComposingText(SinhalaTransliterator.convertWord(composing),1);refreshSuggestions()}}else a?.invoke()}
 private fun finishWord(separator:String){
  buzz(); val word=composing
  if(word.isNotBlank()){val saved=if(english)word else SinhalaTransliterator.convertWord(word);learned.add(word,saved)}
  val ic=currentInputConnection; ic?.finishComposingText()
  if(separator.isNotEmpty()){ic?.commitText(separator,1)} else {
    val info=currentInputEditorInfo
    val multi=(info?.inputType ?: 0) and InputType.TYPE_TEXT_FLAG_MULTI_LINE != 0
    if(multi || info?.imeOptions?.and(EditorInfo.IME_FLAG_NO_ENTER_ACTION)!=0){ic?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));ic?.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP,KeyEvent.KEYCODE_ENTER))}
    else ic?.performEditorAction(info?.imeOptions?.and(EditorInfo.IME_MASK_ACTION) ?: EditorInfo.IME_ACTION_DONE)
  }
  composing="";refreshSuggestions()
 }
 private fun commitWord(t:String){buzz();currentInputConnection?.commitText(apply(t),1);learned.add(t,t);composing="";refreshSuggestions()}
 private fun commitText(t:String){buzz();currentInputConnection?.commitText(apply(t),1);composing="";refreshSuggestions()}
 private fun apply(t:String):String{var s=UnicodeStyles.apply(font,t);return when(deco){1->s.map{"$it̶"}.joinToString("");2->s.map{"$it̲"}.joinToString("");3->s.map{"$it̅"}.joinToString("");4->"「$s」";5->"【$s】";6->"《$s》";7->"⟦$s⟧";8->"『$s』";9->"⟨$s⟩";10->"• $s •";11->"✦ $s ✦";12->"★ $s ★";13->"☆ $s ☆";14->"❖ $s ❖";15->"〔$s〕";16->"〘$s〙";17->"〖$s〗";18->"〚$s〛";19->"⟪$s⟫";20->"⟬$s⟭";else->s}}
 private fun emoji(){val box=panelBox("Emoji");val g=GridLayout(this).apply{columnCount=6};"😀 😃 😄 😁 😆 😅 😂 🤣 😊 😇 🙂 🙃 😉 😌 😍 🥰 😘 😗 😙 😚 😋 😛 😝 😜 🤪 🤨 🧐 🤓 😎 🤩 🥳 😏 😒 😞 😔 😟 😕 🙁 ☹️ 😣 😖 😫 😩 🥺 😢 😭 😤 😠 😡 🤬 🤯 😳 🥵 🥶 😱 😨 😰 😥 😓 🤗 🤔 🤭 🤫 🤥 😶 😐 😑 😬 🙄 😯 😦 😧 😮 😲 🥱 😴 🤤 😪 😵 🤐 🤑 🤠 😈 👿 🤡 💩 👻 💀 👽 🤖 🎃 ❤️ 🧡 💛 💚 💙 💜 🖤 🤍 🤎 💔 💕 💞 💓 💗 💖 💘 💝 💟 👍 👎 👌 ✌️ 🤞 🤟 🤘 👏 🙏 💪 🔥 ⭐ ✨ 🎉 🎊".split(" ").forEach{e->val b=btn(e,key,Color.WHITE){commitText(e)};g.addView(b,GridLayout.LayoutParams().apply{width=0;height=dp(46);columnSpec=GridLayout.spec(GridLayout.UNDEFINED,1f);setMargins(1,1,1,1)})};box.addView(g,LinearLayout.LayoutParams(-1,0,1f));box.addView(btn("← Back",green,bg){panel="";refresh()},LinearLayout.LayoutParams(-1,dp(42)));root.addView(box,LinearLayout.LayoutParams(-1,0,1f))}
 private fun clips(){val box=panelBox("Clipboard History");val items=clips.all();if(items.isEmpty())box.addView(label("No copied text yet"));items.take(30).forEach{box.addView(btn("›  $it   ⧉",key,Color.WHITE){commitText(it)},LinearLayout.LayoutParams(-1,dp(40)))};box.addView(btn("Clear All",green,bg){clips.clear();refresh()},LinearLayout.LayoutParams(-1,dp(42)).apply{setMargins(0,6,0,0)});root.addView(box,LinearLayout.LayoutParams(-1,0,1f))}
 private fun fonts(){val box=panelBox("Font Styles");UnicodeStyles.names().forEachIndexed{i,n->box.addView(btn("${i+1}. $n    ${UnicodeStyles.apply(i,"X Board")}",if(font==i)green else key,if(font==i)bg else Color.WHITE){font=i;panel="";refresh()},LinearLayout.LayoutParams(-1,dp(36)))};root.addView(box,LinearLayout.LayoutParams(-1,0,1f))}
 private fun decos(){val box=panelBox("Text Decoration Styles");val s=listOf("Text","T̶e̶x̶t̶","T̲e̲x̲t̲","T̅e̅x̅t̅","「Text」","【Text】","《Text》","⟦Text⟧","『Text』","⟨Text⟩","• Text •","✦ Text ✦","★ Text ★","☆ Text ☆","❖ Text ❖","〔Text〕","〘Text〙","〖Text〗","〚Text〛","⟪Text⟫");s.forEachIndexed{i,v->box.addView(btn("${i+1}. $v",if(deco==i+1)green else key,if(deco==i+1)bg else Color.WHITE){deco=i+1;panel="";refresh()},LinearLayout.LayoutParams(-1,dp(34)))};root.addView(box,LinearLayout.LayoutParams(-1,0,1f))}
 private fun help(){val box=panelBox("Sinhala Keyboard Guide");listOf("Sinhala phonetic: k=ක්, ka=ක, kA=කැ, ke=කෙ, kee=කේ, ki=කි, kii=කී, ku=කු, kuu=කූ","Mahaapraana: kh=ඛ, gh=ඝ, ch=ඡ, jh=ඣ, Th=ඨ, Dh=ඪ, th=ථ, dh=ධ, ph=ඵ, bh=භ","Conjunct: kY=ක්‍ය්, kYa=ක්‍ය, kE=කෛ, x=ඔ","Space / Enter saves the completed word for future suggestions in both Sinhala and English.","Shift toggles English simple / CAPITAL letters. Toolbar opens each tool panel without changing the keyboard design.").forEach{box.addView(label(it))};box.addView(btn("← Back",green,bg){panel="";refresh()},LinearLayout.LayoutParams(-1,dp(42)));root.addView(box,LinearLayout.LayoutParams(-1,0,1f))}
 private fun panelBox(title:String)=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(5,2,5,2);addView(label(title),LinearLayout.LayoutParams(-1,dp(34)))}
 private fun label(t:String)=TextView(this).apply{text=t;setTextColor(Color.WHITE);textSize=13f;gravity=Gravity.CENTER_VERTICAL;setPadding(8,4,8,4)}
 private fun tool(r:LinearLayout,t:String,a:()->Unit){val b=btn(t,key,Color.WHITE,a);r.addView(b,LinearLayout.LayoutParams(0,dp(38),1f).apply{setMargins(1,0,1,0)})}
 private fun btn(t:String,bg:Int,fg:Int,a:()->Unit)=TextView(this).apply{text=t;setTextColor(fg);textSize=if(t.length>10)10f else 13f;gravity=Gravity.CENTER;setTypeface(Typeface.DEFAULT,Typeface.BOLD);setBackgroundResource(if(bg==green)R.drawable.key_green else R.drawable.key_bg);setOnClickListener{a()}}
 private fun buzz(){if(vibration)(getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(15,VibrationEffect.DEFAULT_AMPLITUDE));if(sound)root.playSoundEffect(0)}
 private fun dp(x:Int)=(x*resources.displayMetrics.density).toInt()
}
