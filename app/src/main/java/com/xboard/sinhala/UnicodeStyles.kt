package com.xboard.sinhala
object UnicodeStyles{
 private val names=listOf("Normal","Bold","Italic","Bold Italic","Script","Fraktur","Double","Bold Fraktur","Sans","Monospace","Sans Bold","Sans Italic","Sans Bold Italic","Sans Mono","Fullwidth","Circled","Squared","Parenthesized","Small Caps","Underline")
 fun names()=names
 fun apply(style:Int,s:String):String{if(style==0)return s; val out=StringBuilder(); for(ch in s){out.append(map(ch,style))}; return when(style){17->out.toString().map{"($it)"}.joinToString("");19->out.toString().map{"$it̲"}.joinToString("");else->out.toString()}}
 private fun map(ch:Char,st:Int):String{if(ch !in 'A'..'Z' && ch !in 'a'..'z')return ch.toString(); val base=when(st){1->0x1D400;2->0x1D434;3->0x1D468;4->0x1D49C;5->0x1D504;6->0x1D538;7->0x1D56C;8->0x1D5A0;9->0x1D670;10->0x1D5A0;11->0x1D608;12->0x1D63C;else->0}; if(base==0)return when(st){14->if(ch.code<=126)(0xFF01+ch.code-33).toChar().toString() else ch.toString();15->if(ch.isLetter())"ⓐ" else ch.toString();16->if(ch.isLetter())"🅰" else ch.toString();18->small(ch);else->ch.toString()}; val off=if(ch.isUpperCase())ch.code-'A'.code else 26+ch.code-'a'.code; return try{String(Character.toChars(base+off))}catch(_:Exception){ch.toString()}}
 private fun small(c:Char)=when(c.lowercaseChar()){'a'->"ᴀ";'b'->"ʙ";'c'->"ᴄ";'d'->"ᴅ";'e'->"ᴇ";'f'->"ғ";'g'->"ɢ";'h'->"ʜ";'i'->"ɪ";'j'->"ᴊ";'k'->"ᴋ";'l'->"ʟ";'m'->"ᴍ";'n'->"ɴ";'o'->"ᴏ";'p'->"ᴘ";'q'->"ǫ";'r'->"ʀ";'s'->"s";'t'->"ᴛ";'u'->"ᴜ";'v'->"ᴠ";'w'->"ᴡ";'x'->"x";'y'->"ʏ";'z'->"ᴢ";else->c.toString()}
}
