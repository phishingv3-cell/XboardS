package com.xboard.sinhala

object UnicodeStyles {

    data class Style(
        val name: String,
        val sample: String,
        val transform: (String) -> String
    )

    private val normalUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val normalLower = "abcdefghijklmnopqrstuvwxyz"

    private val boldUpper = "𝗔𝗕𝗖𝗗𝗘𝗙𝗚𝗛𝗜𝗝𝗞𝗟𝗠𝗡𝗢𝗣𝗤𝗥𝗦𝗧𝗨𝗩𝗪𝗫𝗬𝗭"
    private val boldLower = "𝗮𝗯𝗰𝗱𝗲𝗳𝗴𝗵𝗶𝗷𝗸𝗹𝗺𝗻𝗼𝗽𝗾𝗿𝘀𝘁𝘂𝘃𝘄𝘅𝘆𝘇"
    private val italicUpper = "𝘈𝘉𝘊𝘋𝘌𝘍𝘎𝘏𝘐𝘑𝘒𝘓𝘔𝘕𝘖𝘗𝘘𝘙𝘚𝘛𝘜𝘝𝘞𝘟𝘠𝘡"
    private val italicLower = "𝘢𝘣𝘤𝘥𝘦𝘧𝘨𝘩𝘪𝘫𝘬𝘭𝘮𝘯𝘰𝘱𝘲𝘳𝘴𝘵𝘶𝘷𝘸𝘹𝘺𝘻"
    private val monoUpper = "𝙰𝙱𝙲𝙳𝙴𝙵𝙶𝙷𝙸𝙹𝙺𝙻𝙼𝙽𝙾𝙿𝚀𝚁𝚂𝚃𝚄𝚅𝚆𝚇𝚈𝚉"
    private val monoLower = "𝚊𝚋𝚌𝚍𝚎𝚏𝚐𝚑𝚒𝚓𝚔𝚕𝚖𝚗𝚘𝚙𝚚𝚛𝚜𝚝𝚞𝚟𝚠𝚡𝚢𝚣"

    val all = listOf(
        Style("Normal","X Board") { it },
        Style("Bold","𝗫 𝗕𝗼𝗮𝗿𝗱") { map(it, normalUpper, boldUpper, normalLower, boldLower) },
        Style("Italic","𝘟 𝘉𝘰𝘢𝘳𝘥") { map(it, normalUpper, italicUpper, normalLower, italicLower) },
        Style("Bold Italic","𝑿 𝑩𝒐𝒂𝒓𝒅") { text ->
            text.map { ch ->
                when {
                    ch in normalUpper -> "𝑨𝑩𝑪𝑫𝑬𝑭𝑮𝑯𝑰𝑱𝑲𝑳𝑴𝑵𝑶𝑷𝑸𝑹𝑺𝑻𝑼𝑽𝑾𝑿𝒀𝒁"[normalUpper.indexOf(ch)].toString()
                    ch in normalLower -> "𝒂𝒃𝒄𝒅𝒆𝒇𝒈𝒉𝒊𝒋𝒌𝒍𝒎𝒏𝒐𝒑𝒒𝒓𝒔𝒕𝒖𝒗𝒘𝒙𝒚𝒛"[normalLower.indexOf(ch)].toString()
                    else -> ch.toString()
                }
            }.joinToString("")
        },
        Style("Script","𝒳 𝓑𝓸𝓪𝓻𝓭") { it.map(::script).joinToString("") },
        Style("Fraktur","𝔛 𝔅𝔬𝔞𝔯𝔡") { it.map(::fraktur).joinToString("") },
        Style("Double","𝕏 𝔹𝕠𝕒𝕣𝕕") { it.map(::double).joinToString("") },
        Style("Sans Bold","𝗫 𝗕𝗼𝗮𝗿𝗱") { map(it, normalUpper, boldUpper, normalLower, boldLower) },
        Style("Monospace","𝚇 𝙱𝚘𝚊𝚛𝚍") { map(it, normalUpper, monoUpper, normalLower, monoLower) },
        Style("Small Caps","X BOARD") { it.uppercase() }
    )

    private fun map(
        input: String,
        uFrom: String, uTo: String,
        lFrom: String, lTo: String
    ): String = input.map { ch ->
        when {
            ch in uFrom -> uTo[uFrom.indexOf(ch)].toString()
            ch in lFrom -> lTo[lFrom.indexOf(ch)].toString()
            else -> ch.toString()
        }
    }.joinToString("")

    private fun script(c: Char): String = when (c) {
        in 'A'..'Z' -> "𝒜ℬ𝒞𝒟ℰℱ𝒢ℋℐ𝒥𝒦ℒℳ𝒩𝒪𝒫𝒬ℛ𝒮𝒯𝒰𝒱𝒲𝒳𝒴𝒵"[normalUpper.indexOf(c)].toString()
        in 'a'..'z' -> "𝒶𝒷𝒸𝒹ℯ𝒻ℊ𝒽𝒾𝒿𝓀𝓁𝓂𝓃ℴ𝓅𝓆𝓇𝓈𝓉𝓊𝓋𝓌𝓍𝓎𝓏"[normalLower.indexOf(c)].toString()
        else -> c.toString()
    }

    private fun fraktur(c: Char): String = when (c) {
        in 'A'..'Z' -> "𝔄𝔅ℭ𝔇𝔈𝔉𝔊ℌℑ𝔍𝔎𝔏𝔐𝔑𝔒𝔓𝔔ℜ𝔖𝔗𝔘𝔙𝔚𝔛𝔜ℨ"[normalUpper.indexOf(c)].toString()
        in 'a'..'z' -> "𝔞𝔟𝔠𝔡𝔢𝔣𝔤𝔥𝔦𝔧𝔨𝔩𝔪𝔫𝔬𝔭𝔮𝔯𝔰𝔱𝔲𝔳𝔴𝔵𝔶𝔷"[normalLower.indexOf(c)].toString()
        else -> c.toString()
    }

    private fun double(c: Char): String = when (c) {
        in 'A'..'Z' -> "𝔸𝔹ℂ𝔻𝔼𝔽𝔾ℍ𝕀𝕁𝕂𝕃𝕄ℕ𝕆ℙℚℝ𝕊𝕋𝕌𝕍𝕎𝕏𝕐ℤ"[normalUpper.indexOf(c)].toString()
        in 'a'..'z' -> "𝕒𝕓𝕔𝕕𝕖𝕗𝕘𝕙𝕚𝕛𝕜𝕝𝕞𝕟𝕠𝕡𝕢𝕣𝕤𝕥𝕦𝕧𝕨𝕩𝕪𝕫"[normalLower.indexOf(c)].toString()
        else -> c.toString()
    }
}
