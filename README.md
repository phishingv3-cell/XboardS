# X Board Sinhala Keyboard — FINAL PRO

This project is an Android IME with a fixed dark/neon-green visual system based on the supplied reference screenshot. The layout is intentionally not theme-switchable.

## Platform
- minSdk 26 / Android 8.0+
- targetSdk 35
- Kotlin 2.0.21 / Android Gradle Plugin 8.7.3

## Included tools
- Sinhala ↔ English switch
- English Shift: simple ↔ CAPITAL in the same key layout
- Emoji panel
- Clipboard / learned-word panel with Clear All
- 20 selectable Unicode font styles
- 20 selectable text decoration styles
- Sound ON/OFF
- Vibration ON/OFF
- Help
- Suggestions
- Enter/newline and Space word completion
- Persistent learned words in Sinhala and English
- Sinhala phonetic transliteration including mahaapraana and requested conjunct examples

## Learned suggestions
When a composed word is completed with Space or Enter, the final word is saved. Future typing uses the learned list before fallback suggestions. Data persists across app restarts.

## Test
`tests/TransliteratorTest.kt` is a standalone test for the requested Sinhala phonetic mappings. A `kotlinc` compile/run report is included in `FULL_TEST_REPORT.md`.

## Honest runtime-testing note
A physical Android device/emulator is required for final IME runtime validation. This build was statically validated and the standalone transliteration suite was executed in the available environment; Android UI/IME runtime execution was not available here.
