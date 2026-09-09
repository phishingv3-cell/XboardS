package com.xboard.sinhala

object DecorationStyles {
    data class Decoration(
        val name: String,
        val preview: String,
        val transform: (String) -> String
    )

    val all = listOf(
        Decoration("Stars", "★ Text ★") { "★ $it ★" },
        Decoration("Outline Stars", "☆ Text ☆") { "☆ $it ☆" },
        Decoration("Sparkles", "✦ Text ✦") { "✦ $it ✦" },
        Decoration("Soft Sparkles", "✧ Text ✧") { "✧ $it ✧" },
        Decoration("Hearts", "♡ Text ♡") { "♡ $it ♡" },
        Decoration("Filled Hearts", "♥ Text ♥") { "♥ $it ♥" },
        Decoration("Square Brackets", "【Text】") { "【$it】" },
        Decoration("Corner Brackets", "『Text』") { "『$it』" },
        Decoration("Angle Brackets", "《Text》") { "《$it》" },
        Decoration("Quote Brackets", "「Text」") { "「$it」" },
        Decoration("Round Brackets", "〔Text〕") { "〔$it〕" },
        Decoration("Double Brackets", "⟦Text⟧") { "⟦$it⟧" },
        Decoration("Wings", "༺ Text ༻") { "༺ $it ༻" },
        Decoration("Fancy", "꧁ Text ꧂") { "꧁ $it ꧂" },
        Decoration("Dots", "• Text •") { "• $it •" },
        Decoration("Arrows", "→ Text ←") { "→ $it ←" },
        Decoration("Vertical Arrows", "↑ Text ↓") { "↑ $it ↓" },
        Decoration("Checks", "✓ Text ✓") { "✓ $it ✓" },
        Decoration("Notes", "※ Text ※") { "※ $it ※" },
        Decoration("Glitter", "✨ Text ✨") { "✨ $it ✨" }
    )
}
