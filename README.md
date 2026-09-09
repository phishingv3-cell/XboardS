# X Board Sinhala Keyboard PRO

This is the PRO Kotlin/Android Studio implementation based on the supplied X Board preview.

## Required Sinhala typing is preserved

k   -> ක්
ka  -> ක
kA  -> කැ
ki  -> කි
kI  -> කී
ku  -> කු
kU  -> කූ
ke  -> කෙ
kE  -> කේ
ko  -> කො
kO  -> කෝ

Lowercase is the normal layer. Shift/uppercase is the special/mahaprana layer.

## Toolbar behaviour

This version specifically changes the keyboard like the supplied preview:

- Emoji button -> letter keys disappear, Emoji panel appears.
- Clipboard button -> letter keys disappear, Clipboard History appears.
- B button -> letter keys disappear, 10 font styles appear.
- Decoration button -> letter keys disappear, 20 decoration styles appear.
- Dictionary button -> letter keys disappear, Sinhala keyboard guide appears.
- Help button -> letter keys disappear, Help panel appears.
- Back button -> normal letter keyboard returns.

## App launcher

The launcher is a dark professional X Board settings/home app with:

- Enable Keyboard
- Keyboard Settings
- Help & Guide
- About
- Get Started
- vibration switch
- auto suggestion switch
- Languages & Input
- Clipboard History
- Dictionary
- Advanced styles

## Keyboard

- Android 8+ / API 26+
- Kotlin
- Android InputMethodService
- #07F57E green
- qwerty layout
- Shift layer
- Sinhala composition
- Sinhala/English
- suggestions
- emoji search
- clipboard history
- 10 English text styles
- 20 decoration styles
- dictionary
- help
- symbols
- small vibration
- tap Backspace = delete
- long press Backspace = select all + clear
- Enter = newline only

## File architecture

app/src/main/java/com/xboard/sinhala/
- AppTheme.kt
- KeyboardPreferences.kt
- ClipboardHistory.kt
- SuggestionEngine.kt
- UnicodeStyles.kt
- DecorationStyles.kt
- SinhalaTransliterator.kt
- HelpData.kt
- SimpleTextWatcher.kt
- XBoardImeService.kt
- MainActivity.kt
