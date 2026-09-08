package com.xboard.sinhala

class SinhalaInputEngine {

    private val mappings = LinkedHashMap<String, String>()

    init {
        buildMappings()
    }

    private fun buildMappings() {

        /*
         * ==============================
         * VOWELS
         * ==============================
         */

        add(
            "a" to "අ",
            "aa" to "ආ",
            "ae" to "ඇ",
            "aee" to "ඈ",

            "i" to "ඉ",
            "ii" to "ඊ",

            "u" to "උ",
            "uu" to "ඌ",

            "ru" to "ඍ",
            "ruu" to "ඎ",

            "e" to "එ",
            "ee" to "ඒ",

            "ai" to "ඓ",

            "o" to "ඔ",
            "oo" to "ඕ",

            "au" to "ඖ"
        )

        /*
         * ==============================
         * K
         * ==============================
         */

        addConsonant(
            "k",
            "ක"
        )

        addConsonant(
            "kh",
            "ඛ"
        )

        /*
         * ==============================
         * G
         * ==============================
         */

        addConsonant("g", "ග")
        addConsonant("gh", "ඝ")

        /*
         * ==============================
         * NGA
         * ==============================
         */

        addConsonant("ng", "ඞ")

        /*
         * ==============================
         * CH
         * ==============================
         */

        addConsonant("c", "ච")
        addConsonant("ch", "ඡ")

        /*
         * ==============================
         * J
         * ==============================
         */

        addConsonant("j", "ජ")
        addConsonant("jh", "ඣ")

        /*
         * ==============================
         * NY / GNY
         * ==============================
         */

        addConsonant("ny", "ඤ")
        addConsonant("gn", "ඥ")
        addConsonant("gny", "ඥ")

        /*
         * ==============================
         * RETROFLEX
         * ==============================
         */

        addConsonant("t", "ට")
        addConsonant("th", "ඨ")

        addConsonant("d", "ඩ")
        addConsonant("dh", "ඪ")

        addConsonant("n", "ණ")

        /*
         * ==============================
         * DENTAL
         *
         * t2 / th2 / d2 / dh2 / n2
         * ==============================
         */

        addConsonant("t2", "ත")
        addConsonant("th2", "ථ")

        addConsonant("d2", "ද")
        addConsonant("dh2", "ධ")

        addConsonant("n2", "න")

        /*
         * ඔයා specifically ඉල්ලපු:
         *
         * dha → ධ
         */

        addConsonant("dha", "ධ")

        /*
         * ==============================
         * P
         * ==============================
         */

        addConsonant("p", "ප")
        addConsonant("ph", "ඵ")

        /*
         * ==============================
         * B
         * ==============================
         */

        addConsonant("b", "බ")
        addConsonant("bh", "භ")

        /*
         * ==============================
         * M
         * ==============================
         */

        addConsonant("m", "ම")

        /*
         * ==============================
         * YA / RA / LA / VA
         * ==============================
         */

        addConsonant("y", "ය")
        addConsonant("r", "ර")

        /*
         * L → ල
         */

        addConsonant("l", "ල")

        /*
         * Capital L → ළ
         */

        addConsonant("L", "ළ")

        addConsonant("v", "ව")

        /*
         * ==============================
         * SH / SSHA / S / H / F
         * ==============================
         */

        addConsonant("sh", "ශ")
        addConsonant("ssh", "ෂ")

        addConsonant("s", "ස")
        addConsonant("h", "හ")
        addConsonant("f", "ෆ")

        /*
         * ==============================
         * SPECIAL CONSONANT FORMS
         * ==============================
         */

        add(
            "nga" to "ඞ",
            "nya" to "ඤ",
            "gnya" to "ඥ"
        )

        /*
         * ==============================
         * CONJUNCTS
         * ==============================
         */

        add(
            "kya" to "ක්‍ය",
            "gya" to "ග්‍ය",
            "chya" to "ච්‍ය",
            "jya" to "ජ්‍ය",
            "tya" to "ට්‍ය",
            "dya" to "ඩ්‍ය",
            "thya" to "ත්‍ය",
            "dhya" to "ද්‍ය",
            "pya" to "ප්‍ය",
            "bya" to "බ්‍ය",
            "mya" to "ම්‍ය",
            "vya" to "ව්‍ය"
        )

        /*
         * ==============================
         * R-COMBINATION
         * ==============================
         */

        add(
            "kra" to "ක්‍ර",
            "khaRa" to "ඛ්‍ර",
            "gra" to "ග්‍ර",
            "ghra" to "ඝ්‍ර",

            "chaRa" to "ච්‍ර",
            "jra" to "ජ්‍ර",

            "tra" to "ට්‍ර",
            "dra" to "ඩ්‍ර",

            "thra" to "ත්‍ර",
            "dhra" to "ද්‍ර",

            "pra" to "ප්‍ර",
            "phra" to "ඵ්‍ර",

            "bra" to "බ්‍ර",
            "bhra" to "භ්‍ර",

            "mra" to "ම්‍ර",
            "yra" to "ය්‍ර",

            "lra" to "ල්‍ර",
            "vra" to "ව්‍ර",

            "shra" to "ශ්‍ර",
            "sshra" to "ෂ්‍ර",
            "sra" to "ස්‍ර"
        )

        /*
         * ==============================
         * YANSAYA / SPECIAL
         * ==============================
         */

        add(
            "kya" to "ක්‍ය",
            "kra" to "ක්‍ර",

            "shra" to "ශ්‍ර",

            "dva" to "ද්ව",
            "dwa" to "ද්ව",

            "sva" to "ස්ව",
            "swa" to "ස්ව",

            "tva" to "ත්ව",
            "thva" to "ත්‍ව"
        )

        /*
         * ==============================
         * SPECIAL SIGNS
         * ==============================
         */

        add(
            "n" to "න්",
            "ng" to "ං",
            "h2" to "ඃ",
            "~" to "ං"
        )
    }


    /*
     * Add one or more mappings.
     */

    private fun add(vararg pairs: Pair<String, String>) {

        for ((input, output) in pairs) {
            mappings[input] = output
        }
    }


    /*
     * Add consonant + vowel combinations.
     *
     * Example:
     *
     * k  → ක්
     * ka → ක
     * kaa → කා
     * ke → කෙ
     * kee → කේ
     * ki → කි
     * kii → කී
     * ku → කු
     * kuu → කූ
     * ko → කො
     * koo → කෝ
     */

    private fun addConsonant(
        input: String,
        consonant: String
    ) {

        mappings[input] = consonant + "්"

        mappings[input + "a"] = consonant
        mappings[input + "aa"] = consonant + "ා"

        mappings[input + "ae"] = consonant + "ැ"
        mappings[input + "aee"] = consonant + "ෑ"

        mappings[input + "i"] = consonant + "ි"
        mappings[input + "ii"] = consonant + "ී"

        mappings[input + "u"] = consonant + "ු"
        mappings[input + "uu"] = consonant + "ූ"

        mappings[input + "ru"] = consonant + "ෘ"
        mappings[input + "ruu"] = consonant + "ෲ"

        mappings[input + "e"] = consonant + "ෙ"
        mappings[input + "ee"] = consonant + "ේ"

        mappings[input + "ai"] = consonant + "ෛ"

        mappings[input + "o"] = consonant + "ො"
        mappings[input + "oo"] = consonant + "ෝ"

        mappings[input + "au"] = consonant + "ෞ"

        /*
         * Yansaya
         */

        mappings[input + "ya"] =
            consonant + "්‍ය"

        /*
         * Rakaransaya
         */

        mappings[input + "ra"] =
            consonant + "්‍ර"
    }


    /*
     * Convert a complete phonetic string.
     *
     * Longest match is used first.
     *
     * Example:
     *
     * "k"    → ක්
     * "ka"   → ක
     * "kaa"  → කා
     * "krii" → ක්‍රී
     */

    fun convert(input: String): String {

        if (input.isEmpty()) {
            return ""
        }

        var text = input

        val result = StringBuilder()

        /*
         * Longest input first.
         */

        val keys = mappings.keys
            .sortedByDescending { it.length }

        while (text.isNotEmpty()) {

            var matchedKey: String? = null

            for (key in keys) {

                if (text.startsWith(key)) {
                    matchedKey = key
                    break
                }
            }

            if (matchedKey != null) {

                result.append(
                    mappings[matchedKey]
                )

                text = text.substring(
                    matchedKey.length
                )

            } else {

                /*
                 * No Sinhala mapping found.
                 * Keep original character.
                 */

                result.append(text[0])

                text = text.substring(1)
            }
        }

        return result.toString()
    }


    /*
     * Check whether a partial input can become
     * a Sinhala character.
     *
     * Useful for live typing.
     */

    fun hasPrefix(input: String): Boolean {

        if (input.isEmpty()) {
            return false
        }

        return mappings.keys.any {
            it.startsWith(input)
        }
    }


    /*
     * Get direct mapping.
     */

    fun get(input: String): String? {
        return mappings[input]
    }
}