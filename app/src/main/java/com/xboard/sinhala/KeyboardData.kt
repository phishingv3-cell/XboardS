package com.xboard.sinhala

data class KeyData(
    val english: String,
    val sinhala: String,     // සිංහලෙන් ටයිප් වෙද්දී වැටෙන අකුර
    val sinhalaHint: String  // උඩින් පෙනෙන hint එක
)

object KeyboardData {

    val rows = listOf(

        listOf(
            KeyData("Q", "ඤ", "ඤ"),
            KeyData("W", "ව්", "ව්"),
            KeyData("E", "එ", "එ"),
            KeyData("R", "ර්", "ර්"),
            KeyData("T", "ට්", "ට්"),
            KeyData("Y", "ය්", "ය්"),
            KeyData("U", "උ", "උ"),
            KeyData("I", "ඉ", "ඉ"),
            KeyData("O", "ඔ", "ඔ"),
            KeyData("P", "ප්", "ප්")
        ),

        listOf(
            KeyData("A", "අ", "අ"),
            KeyData("S", "ස්", "ස්"),
            KeyData("D", "ද්", "ද්"),
            KeyData("F", "ෆ්", "ෆ්"),
            KeyData("G", "ග්", "ග්"),
            KeyData("H", "හ්", "හ්"),
            KeyData("J", "ජ්", "ජ්"),
            KeyData("K", "ක්", "ක්"),
            KeyData("L", "ල්", "ල්")
        ),

        listOf(
            KeyData("Z", "ෂ්", "ෂ්"),
            KeyData("X", "ශ්", "ශ්"),
            KeyData("C", "ච්", "ච්"),
            KeyData("V", "ව්", "ව්"),
            KeyData("B", "බ්", "බ්"),
            KeyData("N", "න්", "න්"),
            KeyData("M", "ම්", "ම්")
        )
    )
}
