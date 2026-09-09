# X Board Sinhala Keyboard — PRO FINAL test report

Reference screenshot inspected: `DESIGN_REFERENCE.png` (1536×1024).

## Verified in this environment
- Android `minSdk 26` = Android 8.0+
- IME service + two subtypes (Sinhala/English) declared
- Toolbar actions present: Sinhala↔English, Emoji, Clipboard, Font, Decoration, Sound, Vibration, Help
- English Shift toggles lowercase/UPPERCASE in the same third keyboard row
- Space and Enter finish/save the current word to persistent learned-word storage
- Learned suggestions search both the typed phonetic key and saved display word
- Clipboard history uses Android ClipboardManager and has Clear All
- 20 font-style entries
- 20 decoration-style entries
- Sinhala transliteration standalone suite: **PASS 25 cases**
- Unicode font suite: **PASS 20 styles**
- Static source sanity: **PASS**

## Requested Sinhala mappings covered by the test suite
`k→ක්`, `ka→ක`, `kA→කැ`, `ke→කෙ`, `kee→කේ`, `ki→කි`, `kii→කී`, `ku→කු`, `kuu→කූ`, `kY→ක්‍ය්`, `kYa→ක්‍ය`, `kE→කෛ`, plus mahaapraana `kh/gh/ch/jh/Th/Dh/th/dh/ph/bh`, `Sh/sh`, and `x→ඔ`.

## Important limitation
This environment does not contain an Android SDK/emulator, so a real APK install, IME activation, touch-event test, and rendering/pixel comparison cannot be honestly reported as runtime-tested. The project is prepared for Android Studio/SDK 35 and Android 8.0+.
