package com.xboard.sinhala

class SuggestionEngine(
    private val storage: XBoardStorage
) {

    private val sinhala =
        listOf(

            "මම",
            "ඔබ",
            "අපි",
            "අද",
            "හෙට",
            "ගෙදර",
            "පාසල",
            "වැඩ",
            "හොඳයි",
            "කොහොමද",
            "ආයුබෝවන්",
            "ස්තූතියි",
            "කරුණාකර",
            "ඔව්",
            "නැහැ",
            "හොඳ",
            "ඇයි",
            "කියන්න",
            "කරන්න",
            "යන්න",
            "එන්න"
        )


    private val english =
        listOf(

            "hello",
            "how",
            "are",
            "you",
            "good",
            "morning",
            "thank",
            "thanks",
            "please",
            "welcome",
            "today",
            "tomorrow",
            "home",
            "school",
            "work",
            "yes",
            "no"
        )


    fun suggest(
        prefix: String
    ): List<String> {

        if (prefix.isBlank()) {
            return emptyList()
        }

        val learned =
            storage
                .learnedWords()
                .filter {
                    it.startsWith(
                        prefix,
                        ignoreCase = true
                    )
                }


        val dictionary =
            (sinhala + english)
                .filter {
                    it.startsWith(
                        prefix,
                        ignoreCase = true
                    )
                }


        return (
            learned + dictionary
        )
            .distinct()
            .take(6)
    }
}