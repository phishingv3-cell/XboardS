package com.xboard.sinhala

object SinhalaAlphabet {

    // සිංහල ස්වර
    val vowels = listOf(
        "අ", "ආ",
        "ඇ", "ඈ",
        "ඉ", "ඊ",
        "උ", "ඌ",
        "ඍ", "ඎ",
        "ඏ", "ඐ",
        "එ", "ඒ",
        "ඓ",
        "ඔ", "ඕ",
        "ඖ"
    )

    // සිංහල ව්‍යංජන
    val consonants = listOf(
        "ක", "ඛ", "ග", "ඝ", "ඞ",
        "ච", "ඡ", "ජ", "ඣ", "ඤ", "ඥ",
        "ට", "ඨ", "ඩ", "ඪ", "ණ",
        "ත", "ථ", "ද", "ධ", "න",
        "ප", "ඵ", "බ", "භ", "ම",
        "ය", "ර", "ල", "ව",
        "ශ", "ෂ", "ස", "හ", "ළ", "ෆ"
    )

    // පිලි / ස්වර ලකුණු
    val vowelSigns = listOf(
        "",
        "ා",
        "ැ",
        "ෑ",
        "ි",
        "ී",
        "ු",
        "ූ",
        "ෘ",
        "ෲ",
        "ෙ",
        "ේ",
        "ෛ",
        "ො",
        "ෝ",
        "ෞ"
    )

    // වෙනත් ලකුණු
    val specialSigns = listOf(
        "්",
        "ං",
        "ඃ",
        "ෟ",
        "ෳ"
    )

    // සියල්ල
    val allCharacters: List<String>
        get() = vowels + consonants + vowelSigns + specialSigns
}