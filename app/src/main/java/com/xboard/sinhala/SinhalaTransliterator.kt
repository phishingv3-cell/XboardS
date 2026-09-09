package com.xboard.sinhala

object SinhalaTransliterator {
 private val c=linkedMapOf(
  "kh" to "ඛ","gh" to "ඝ","ch" to "ඡ","jh" to "ඣ","Th" to "ඨ","Dh" to "ඪ","th" to "ථ","dh" to "ධ","ph" to "ඵ","bh" to "භ","Sh" to "ෂ","sh" to "ශ",
  "ng" to "ඟ","gn" to "ඥ","Gn" to "ඤ","Ny" to "ඤ","k" to "ක","g" to "ග","c" to "ච","j" to "ජ","t" to "ත","d" to "ද","p" to "ප","b" to "බ","m" to "ම","n" to "න","N" to "ණ","T" to "ට","D" to "ඩ","P" to "ප","B" to "බ","y" to "ය","r" to "ර","l" to "ල","L" to "ළ","v" to "ව","w" to "ව","s" to "ස","h" to "හ","f" to "ෆ","q" to "ඤ","x" to "ඔ")
 private val vowels=mapOf("a" to "","aa" to "ා","A" to "ැ","ae" to "ෑ","e" to "ෙ","ee" to "ේ","E" to "ෛ","i" to "ි","ii" to "ී","u" to "ු","uu" to "ූ","U" to "ූ","o" to "ො","oo" to "ෝ","O" to "ඕ")
 private val standalone=mapOf("a" to "අ","aa" to "ආ","A" to "ඇ","ae" to "ඈ","i" to "ඉ","ii" to "ඊ","u" to "උ","uu" to "ඌ","e" to "එ","ee" to "ඒ","E" to "ඓ","o" to "ඔ","oo" to "ඕ","x" to "ඔ")
 private fun tokenAt(s:String,i:Int):Pair<String,String>?{for((k,v) in c.entries.sortedByDescending{it.key.length}) if(s.startsWith(k,i)) return k to v; return null}
 fun convertWord(s:String):String{
  if(s.isEmpty())return s
  val special=mapOf("kY" to "ක්‍ය්","kYa" to "ක්‍ය","kYaa" to "ක්‍යා","kYe" to "ක්‍යෙ","kYee" to "ක්‍යේ","kYi" to "ක්‍යි","kYii" to "ක්‍යී","kYu" to "ක්‍යු","kYuu" to "ක්‍යූ")
  special[s]?.let{return it}
  if(s.all{it.isLetter() && it.toString() !in c.keys}) standalone[s]?.let{return it}
  var i=0; val out=StringBuilder();
  while(i<s.length){
   val hit=tokenAt(s,i)
   if(hit==null){out.append(s[i]);i++;continue}
   val (tok,base)=hit; i+=tok.length
   if(i<s.length && s[i]=='Y'){i++;out.append(base).append('්').append("‍ය්"); if(i<s.length && s[i]=='a'){i++;out.append("");} ;continue}
   var matched:String?=null
   for(v in vowels.keys.sortedByDescending{it.length}) if(s.startsWith(v,i)){matched=v;break}
   if(matched!=null){i+=matched.length; val mark=vowels[matched]!!; if(mark.isEmpty())out.append(base) else out.append(base+mark)}
   else if(tok.length > 1 || tok == "x") out.append(base) else out.append(base).append('්')
  }
  return out.toString().replace("","")
 }
}
