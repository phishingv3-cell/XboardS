package com.xboard.sinhala

object SuggestionEngine {

    private val sinhala = listOf(
        "මම","මගේ","මට","මටත්","ඔයා","ඔයාට","ඔබ","අපි","අපේ",
        "අද","හෙට","දැන්","හොඳ","හොඳයි","කොහොමද","කොහෙද","කවදද",
        "මොකද","ඇයි","හරි","ඔව්","නෑ","එන්න","යන්න","ඉන්න","කියන්න",
        "කරන්න","කන්න","බොන්න","ස්තූතියි","කරුණාකර","ලස්සනයි"
    )

    private val english = listOf(
        "hello","hi","how","how are you","good","great","thanks","thank you",
        "please","sorry","okay","yes","no","today","tomorrow","this","that",
        "what","when","where","why","keyboard","sinhala","english","school",
        "study","friend","welcome"
    )

    fun suggest(query: String, sinhalaMode: Boolean): List<String> {
        val pool = if (sinhalaMode) sinhala else english
        if (query.isBlank()) return pool.take(6)
        val q = query.lowercase()
        return pool.filter { it.lowercase().startsWith(q) || it.lowercase().contains(q) }.take(6)
    }
}
