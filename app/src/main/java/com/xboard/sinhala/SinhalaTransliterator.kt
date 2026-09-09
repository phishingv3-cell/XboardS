package com.xboard.sinhala

/**
 * The requested X Board phonetic system is kept unchanged:
 *
 * k   -> ක්
 * ka  -> ක
 * kA  -> කැ
 * ki  -> කි
 * kI  -> කී
 * ku  -> කු
 * kU  -> කූ
 * ke  -> කෙ
 * kE  -> කේ
 * ko  -> කො
 * kO  -> කෝ
 *
 * Lowercase = normal layer.
 * Shift/uppercase = mahaprana or special layer.
 */
object SinhalaTransliterator {

    private val independent = mapOf(
        "a" to "අ", "A" to "ඇ",
        "i" to "ඉ", "I" to "ඊ",
        "u" to "උ", "U" to "ඌ",
        "e" to "එ", "E" to "ඒ",
        "o" to "ඔ", "O" to "ඕ"
    )

    private val consonants = mapOf(
        "k" to "ක",
        "g" to "ග",
        "c" to "ච",
        "j" to "ජ",
        "t" to "ට",
        "d" to "ඩ",
        "T" to "ත",
        "D" to "ද",
        "p" to "ප",
        "b" to "බ",
        "m" to "ම",
        "n" to "න",
        "N" to "ණ",
        "y" to "ය",
        "r" to "ර",
        "l" to "ල",
        "L" to "ළ",
        "w" to "ව",
        "v" to "ව",
        "s" to "ස",
        "h" to "හ",
        "f" to "ෆ"
    )

    private val shifted = mapOf(
        "K" to "ඛ",
        "G" to "ඝ",
        "C" to "ඡ",
        "J" to "ඣ",
        "t" to "ඨ",
        "d" to "ඪ",
        "P" to "ඵ",
        "B" to "භ",
        "N" to "ණ",
        "L" to "ළ",
        "S" to "ෂ",
        "F" to "ෆ"
    )

    private val marks = mapOf(
        "a" to "",
        "A" to "ැ",
        "i" to "ි",
        "I" to "ී",
        "u" to "ු",
        "U" to "ූ",
        "e" to "ෙ",
        "E" to "ේ",
        "o" to "ො",
        "O" to "ෝ"
    )

    private val aliases = linkedMapOf(
        "nga" to "ඟ",
        "nda" to "ඳ",
        "nDa" to "ඬ",
        "mba" to "ඹ",
        "nya" to "ඤ",
        "jna" to "ඥ",
        "tha" to "ත",
        "dha" to "ද",
        "sha" to "ශ",
        "sHa" to "ෂ",
        "ksha" to "ක්ෂ",
        "tra" to "ත්‍ර",
        "thra" to "ත්‍ර",
        "dra" to "ද්‍ර",
        "dhra" to "ධ්‍ර",
        "kra" to "ක්‍ර",
        "gra" to "ග්‍ර",
        "pra" to "ප්‍ර",
        "phra" to "ඵ්‍ර",
        "bra" to "බ්‍ර",
        "bhra" to "භ්‍ර",
        "mra" to "ම්‍ර",
        "sra" to "ස්‍ර",
        "shra" to "ශ්‍ර",
        "kya" to "ක්‍ය",
        "gya" to "ග්‍ය",
        "dya" to "ද්‍ය",
        "vya" to "ව්‍ය",
        "nya2" to "න්‍ය",
        "rya" to "ර්‍ය",
        "lya" to "ල්‍ය"
    )

    fun convertToken(token: String): String? {
        aliases[token]?.let { return it }
        independent[token]?.let { return it }

        if (token.length >= 2) {
            val key = token.dropLast(1)
            val vowel = token.last().toString()
            val base = shifted[key] ?: consonants[key]
            val mark = marks[vowel]
            if (base != null && mark != null) return base + mark
        }

        if (token.length == 1) {
            shifted[token]?.let { return it }
            consonants[token]?.let { return it + "්" }
            independent[token]?.let { return it }
        }

        return null
    }

    fun convertWord(input: String): String {
        if (input.isEmpty()) return ""
        val out = StringBuilder()
        var remaining = input

        while (remaining.isNotEmpty()) {
            var chosen: Pair<String, String>? = null
            val max = minOf(8, remaining.length)

            for (size in max downTo 1) {
                val token = remaining.take(size)
                val value = convertToken(token)
                if (value != null) {
                    chosen = token to value
                    break
                }
            }

            if (chosen == null) {
                out.append(remaining.first())
                remaining = remaining.drop(1)
            } else {
                out.append(chosen.second)
                remaining = remaining.drop(chosen.first.length)
            }
        }
        return out.toString()
    }

    fun guide(): List<Pair<String, String>> = listOf(
        "ක්" to "k",
        "ක" to "ka",
        "කැ" to "kA",
        "කි" to "ki",
        "කී" to "kI",
        "කු" to "ku",
        "කූ" to "kU",
        "කෙ" to "ke",
        "කේ" to "kE",
        "කො" to "ko",
        "කෝ" to "kO",
        "ඛ" to "SHIFT + K",
        "ඝ" to "SHIFT + G",
        "ඡ" to "SHIFT + C",
        "ඣ" to "SHIFT + J",
        "ඨ" to "SHIFT + T",
        "ඪ" to "SHIFT + D",
        "ඵ" to "SHIFT + P",
        "භ" to "SHIFT + B",
        "ණ" to "SHIFT + N",
        "ළ" to "SHIFT + L",
        "ශ" to "SHIFT + S + H + A",
        "ෂ" to "SHIFT + S",
        "ෆ" to "SHIFT + F",
        "ඟ" to "nga",
        "ඳ" to "nda",
        "ඬ" to "nDa",
        "ඹ" to "mba",
        "ඤ" to "nya",
        "ඥ" to "jna",
        "ත්‍ර" to "tra",
        "ශ්‍ර" to "shra",
        "ක්ෂ" to "ksha"
    )
}
