package com.xboard.sinhala

data class KeyData(
    val english: String,
    val sinhalaHint: String
)

object KeyboardData {

    val rows = listOf(

        listOf(
            KeyData("Q", "ඤ"),
            KeyData("W", "ව්"),
            KeyData("E", "එ"),
            KeyData("R", "ර්"),
            KeyData("T", "ට්"),
            KeyData("Y", "ය්"),
            KeyData("U", "උ"),
            KeyData("I", "ඉ"),
            KeyData("O", "ඔ"),
            KeyData("P", "ප්")
        ),

        listOf(
            KeyData("A", "අ"),
            KeyData("S", "ස්"),
            KeyData("D", "ද්"),
            KeyData("F", "ෆ්"),
            KeyData("G", "ග්"),
            KeyData("H", "හ්"),
            KeyData("J", "ජ්"),
            KeyData("K", "ක්"),
            KeyData("L", "ල්")
        ),

        listOf(
            KeyData("Z", "ෂ්"),
            KeyData("X", "ශ්"),
            KeyData("C", "ච්"),
            KeyData("V", "ව්"),
            KeyData("B", "බ්"),
            KeyData("N", "න්"),
            KeyData("M", "ම්")
        )
    )
}