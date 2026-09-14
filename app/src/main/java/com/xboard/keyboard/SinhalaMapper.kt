object SinhalaMapper {

    private val mapping = mapOf(

        // =========================
        // ක
        // =========================
        "k" to "ක්", "ka" to "ක", "kA" to "කැ", "kAa" to "කෑ",
        "ki" to "කි", "kii" to "කී", "ku" to "කු", "kuu" to "කූ",
        "ke" to "කෙ", "kee" to "කේ", "kY" to "ක්‍ය්", "kYa" to "ක්‍ය",
        "ko" to "කො", "koo" to "කෝ", "kE" to "කෛ",
        "kra" to "ක්‍ර", "krA" to "ක්‍රැ", "krAa" to "ක්‍රෑ",
        "kri" to "ක්‍රි", "krii" to "ක්‍රී", "kru" to "කෘ",
        "kruu" to "කෲ", "kre" to "ක්‍රෙ", "kree" to "ක්‍රේ",
        "kro" to "ක්‍රො", "kroo" to "ක්‍රෝ", "krE" to "ක්‍රෛ",
        "kau" to "කෞ",

        // =========================
        // ග
        // =========================
        "g" to "ග්", "ga" to "ග", "gA" to "ගැ", "gAa" to "ගෑ",
        "gi" to "ගි", "gii" to "ගී", "gu" to "ගු", "guu" to "ගූ",
        "ge" to "ගෙ", "gee" to "ගේ", "gY" to "ග්‍ය්", "gYa" to "ග්‍ය",
        "go" to "ගො", "goo" to "ගෝ", "gE" to "ගෛ",
        "gra" to "ග්‍ර", "grA" to "ග්‍රැ", "grAa" to "ග්‍රෑ",
        "gri" to "ග්‍රි", "grii" to "ග්‍රී", "gru" to "ගෘ",
        "gruu" to "ගෲ", "gre" to "ග්‍රෙ", "gree" to "ග්‍රේ",
        "gro" to "ග්‍රො", "groo" to "ග්‍රෝ", "grE" to "ග්‍රෛ",
        "gau" to "ගෞ",

        // =========================
        // ච
        // =========================
        "c" to "ච්", "ca" to "ච", "cA" to "චැ", "cAa" to "චෑ",
        "ci" to "චි", "cii" to "චී", "cu" to "චු", "cuu" to "චූ",
        "ce" to "චෙ", "cee" to "චේ", "cY" to "ච්‍ය්", "cYa" to "ච්‍ය",
        "co" to "චො", "coo" to "චෝ", "cE" to "චෛ",
        "cra" to "ච්‍ර", "crA" to "ච්‍රැ", "crAa" to "ච්‍රෑ",
        "cri" to "ච්‍රි", "crii" to "ච්‍රී", "cru" to "චෘ",
        "cruu" to "චෲ", "cre" to "ච්‍රෙ", "cree" to "ච්‍රේ",
        "cro" to "ච්‍රො", "croo" to "ච්‍රෝ", "crE" to "ච්‍රෛ",
        "cau" to "චෞ",

        // =========================
        // ජ
        // =========================
        "j" to "ජ්", "ja" to "ජ", "jA" to "ජැ", "jAa" to "ජෑ",
        "ji" to "ජි", "jii" to "ජී", "ju" to "ජු", "juu" to "ජූ",
        "je" to "ජෙ", "jee" to "ජේ", "jY" to "ජ්‍ය්", "jYa" to "ජ්‍ය",
        "jo" to "ජො", "joo" to "ජෝ", "jE" to "ජෛ",
        "jra" to "ජ්‍ර", "jrA" to "ජ්‍රැ", "jrAa" to "ජ්‍රෑ",
        "jri" to "ජ්‍රි", "jrii" to "ජ්‍රී", "jru" to "ජෘ",
        "jruu" to "ජෲ", "jre" to "ජ්‍රෙ", "jree" to "ජ්‍රේ",
        "jro" to "ජ්‍රො", "jroo" to "ජ්‍රෝ", "jrE" to "ජ්‍රෛ",
        "jau" to "ජෞ",

        // =========================
        // ත
        // =========================
        "t" to "ත්", "ta" to "ත", "tA" to "තැ", "tAa" to "තෑ",
        "ti" to "ති", "tii" to "තී", "tu" to "තු", "tuu" to "තූ",
        "te" to "තෙ", "tee" to "තේ", "tY" to "ත්‍ය්", "tYa" to "ත්‍ය",
        "to" to "තො", "too" to "තෝ", "tE" to "තෛ",
        "tra" to "ත්‍ර", "trA" to "ත්‍රැ", "trAa" to "ත්‍රෑ",
        "tri" to "ත්‍රි", "trii" to "ත්‍රී", "tru" to "ත්‍රෘ",
        "truu" to "ත්‍රෲ", "tre" to "ත්‍රෙ", "tree" to "ත්‍රේ",
        "tro" to "ත්‍රො", "troo" to "ත්‍රෝ", "trE" to "ත්‍රෛ",
        "tau" to "තෞ",

        // =========================
        // ද
        // =========================
        "d" to "ද්", "da" to "ද", "dA" to "දැ", "dAa" to "දෑ",
        "di" to "දි", "dii" to "දී", "du" to "දු", "duu" to "දූ",
        "de" to "දෙ", "dee" to "දේ", "dY" to "ද්‍ය්", "dYa" to "ද්‍ය",
        "do" to "දො", "doo" to "දෝ", "dE" to "දෛ",
        "dra" to "ද්‍ර", "drA" to "ද්‍රැ", "drAa" to "ද්‍රෑ",
        "dri" to "ද්‍රි", "drii" to "ද්‍රී", "dru" to "ද්‍රෘ",
        "druu" to "ද්‍රෲ", "dre" to "ද්‍රෙ", "dree" to "ද්‍රේ",
        "dro" to "ද්‍රො", "droo" to "ද්‍රෝ", "drE" to "ද්‍රෛ",
        "dau" to "දෞ",

        // =========================
        // න
        // =========================
        "n" to "න්", "na" to "න", "nA" to "නැ", "nAa" to "නෑ",
        "ni" to "නි", "nii" to "නී", "nu" to "නු", "nuu" to "නූ",
        "ne" to "නෙ", "nee" to "නේ", "nY" to "න්‍ය්", "nYa" to "න්‍ය",
        "no" to "නො", "noo" to "නෝ", "nE" to "නෛ",
        "nra" to "න්‍ර", "nrA" to "න්‍රැ", "nrAa" to "න්‍රෑ",
        "nri" to "න්‍රි", "nrii" to "න්‍රී", "nru" to "න්‍රෘ",
        "nruu" to "න්‍රෲ", "nre" to "න්‍රෙ", "nree" to "න්‍රේ",
        "nro" to "න්‍රො", "nroo" to "න්‍රෝ", "nrE" to "න්‍රෛ",
        "nau" to "නෞ",

        // =========================
        // ප
        // =========================
        "p" to "ප්", "pa" to "ප", "pA" to "පැ", "pAa" to "පෑ",
        "pi" to "පි", "pii" to "පී", "pu" to "පු", "puu" to "පූ",
        "pe" to "පෙ", "pee" to "පේ", "pY" to "ප්‍ය්", "pYa" to "ප්‍ය",
        "po" to "පො", "poo" to "පෝ", "pE" to "පෛ",
        "pra" to "ප්‍ර", "prA" to "ප්‍රැ", "prAa" to "ප්‍රෑ",
        "pri" to "ප්‍රි", "prii" to "ප්‍රී", "pru" to "ප්‍රෘ",
        "pruu" to "ප්‍රෲ", "pre" to "ප්‍රෙ", "pree" to "ප්‍රේ",
        "pro" to "ප්‍රො", "proo" to "ප්‍රෝ", "prE" to "ප්‍රෛ",
        "pau" to "පෞ",

        // =========================
        // බ
        // =========================
        "b" to "බ්", "ba" to "බ", "bA" to "බැ", "bAa" to "බෑ",
        "bi" to "බි", "bii" to "බී", "bu" to "බු", "buu" to "බූ",
        "be" to "බෙ", "bee" to "බේ", "bY" to "බ්‍ය්", "bYa" to "බ්‍ය",
        "bo" to "බො", "boo" to "බෝ", "bE" to "බෛ",
        "bra" to "බ්‍ර", "brA" to "බ්‍රැ", "brAa" to "බ්‍රෑ",
        "bri" to "බ්‍රි", "brii" to "බ්‍රී", "bru" to "බෘ",
        "bruu" to "බෲ", "bre" to "බ්‍රෙ", "bree" to "බ්‍රේ",
        "bro" to "බ්‍රො", "broo" to "බ්‍රෝ", "brE" to "බ්‍රෛ",
        "bau" to "බෞ",

        // =========================
        // ම
        // =========================
        "m" to "ම්", "ma" to "ම", "mA" to "මැ", "mAa" to "මෑ",
        "mi" to "මි", "mii" to "මී", "mu" to "මු", "muu" to "මූ",
        "me" to "මෙ", "mee" to "මේ", "mY" to "ම්‍ය්", "mYa" to "ම්‍ය",
        "mo" to "මො", "moo" to "මෝ", "mE" to "මෛ",
        "mra" to "ම්‍ර", "mrA" to "ම්‍රැ", "mrAa" to "ම්‍රෑ",
        "mri" to "ම්‍රි", "mrii" to "ම්‍රී", "mru" to "ම්‍රෘ",
        "mruu" to "ම්‍රෲ", "mre" to "ම්‍රෙ", "mree" to "ම්‍රේ",
        "mro" to "ම්‍රො", "mroo" to "ම්‍රෝ", "mrE" to "ම්‍රෛ",
        "mau" to "මෞ",

        // =========================
        // ය
        // =========================
        "y" to "ය්", "ya" to "ය", "yA" to "යැ", "yAa" to "යෑ",
        "yi" to "යි", "yii" to "යී", "yu" to "යු", "yuu" to "යූ",
        "ye" to "යෙ", "yee" to "යේ", "yo" to "යො", "yoo" to "යෝ",
        "yE" to "යෛ", "yau" to "යෞ",

        // =========================
        // ර
        // =========================
        "r" to "ර්", "ra" to "ර", "rA" to "රැ", "rAa" to "රෑ",
        "ri" to "රි", "rii" to "රී", "ru" to "රු", "ruu" to "රූ",
        "re" to "රෙ", "ree" to "රේ", "ro" to "රො", "roo" to "රෝ",
        "rE" to "රෛ", "rau" to "රෞ",

        // =========================
        // ල
        // =========================
        "l" to "ල්", "la" to "ල", "lA" to "ලැ", "lAa" to "ලෑ",
        "li" to "ලි", "lii" to "ලී", "lu" to "ලු", "luu" to "ලූ",
        "le" to "ලෙ", "lee" to "ලේ", "lo" to "ලො", "loo" to "ලෝ",
        "lE" to "ලෛ", "lau" to "ලෞ",

        // =========================
        // ව
        // =========================
        "v" to "ව්", "va" to "ව", "vA" to "වැ", "vAa" to "වෑ",
        "vi" to "වි", "vii" to "වී", "vu" to "වු", "vuu" to "වූ",
        "ve" to "වෙ", "vee" to "වේ", "vo" to "වො", "voo" to "වෝ",
        "vE" to "වෛ", "vau" to "වෞ",

        // =========================
        // ස
        // =========================
        "s" to "ස්", "sa" to "ස", "sA" to "සැ", "sAa" to "සෑ",
        "si" to "සි", "sii" to "සී", "su" to "සු", "suu" to "සූ",
        "se" to "සෙ", "see" to "සේ", "sY" to "ස්‍ය්", "sYa" to "ස්‍ය",
        "so" to "සො", "soo" to "සෝ", "sE" to "සෛ",
        "sra" to "ස්‍ර", "srA" to "ස්‍රැ", "srAa" to "ස්‍රෑ",
        "sri" to "ස්‍රි", "srii" to "ස්‍රී", "sru" to "ස්‍රෘ",
        "sruu" to "ස්‍රෲ", "sre" to "ස්‍රෙ", "sree" to "ස්‍රේ",
        "sro" to "ස්‍රො", "sroo" to "ස්‍රෝ", "srE" to "ස්‍රෛ",
        "sau" to "සෞ",

        // =========================
        // හ
        // =========================
        "h" to "හ්", "ha" to "හ", "hA" to "හැ", "hAa" to "හෑ",
        "hi" to "හි", "hii" to "හී", "hu" to "හු", "huu" to "හූ",
        "he" to "හෙ", "hee" to "හේ", "hY" to "හ්‍ය්", "hYa" to "හ්‍ය",
        "ho" to "හො", "hoo" to "හෝ", "hE" to "හෛ",
        "hra" to "හ්‍ර", "hrA" to "හ්‍රැ", "hrAa" to "හ්‍රෑ",
        "hri" to "හ්‍රි", "hrii" to "හ්‍රී", "hru" to "හෘ",
        "hruu" to "හෲ", "hre" to "හ්‍රෙ", "hree" to "හ්‍රේ",
        "hro" to "හ්‍රො", "hroo" to "හ්‍රෝ", "hrE" to "හ්‍රෛ",
        "hau" to "හෞ",

        // =========================
        // ඛ
        // =========================
        "K" to "ඛ්", "Ka" to "ඛ", "KA" to "ඛැ", "KAa" to "ඛෑ",
        "Ki" to "ඛි", "Kii" to "ඛී", "Ku" to "ඛු", "Kuu" to "ඛූ",
        "Ke" to "ඛෙ", "Kee" to "ඛේ", "KY" to "ඛ්‍ය්", "KYa" to "ඛ්‍ය",
        "Ko" to "ඛො", "Koo" to "ඛෝ", "KE" to "ඛෛ",
        "Kra" to "ඛ්‍ර", "KrA" to "ඛ්‍රැ", "KrAa" to "ඛ්‍රෑ",
        "Kri" to "ඛ්‍රි", "Krii" to "ඛ්‍රී", "Kru" to "ඛෘ",
        "Kruu" to "ඛෲ", "Kre" to "ඛ්‍රෙ", "Kree" to "ඛ්‍රේ",
        "Kro" to "ඛ්‍රො", "Kroo" to "ඛ්‍රෝ", "KrE" to "ඛ්‍රෛ",
        "Kau" to "ඛෞ",

        // =========================
        // ඝ
        // =========================
        "G" to "ඝ්", "Ga" to "ඝ", "GA" to "ඝැ", "GAa" to "ඝෑ",
        "Gi" to "ඝි", "Gii" to "ඝී", "Gu" to "ඝු", "Guu" to "ඝූ",
        "Ge" to "ඝෙ", "Gee" to "ඝේ", "GY" to "ඝ්‍ය්", "GYa" to "ඝ්‍ය",
        "Go" to "ඝො", "Goo" to "ඝෝ", "GE" to "ඝෛ",
        "Gra" to "ඝ්‍ර", "GrA" to "ඝ්‍රැ", "GrAa" to "ඝ්‍රෑ",
        "Gri" to "ඝ්‍රි", "Grii" to "ඝ්‍රී", "Gru" to "ඝෘ",
        "Gruu" to "ඝෲ", "Gre" to "ඝ්‍රෙ", "Gree" to "ඝ්‍රේ",
        "Gro" to "ඝ්‍රො", "Groo" to "ඝ්‍රෝ", "GrE" to "ඝ්‍රෛ",
        "Gau" to "ඝෞ",

        // =========================
        // ඡ
        // =========================
        "C" to "ඡ්", "Ca" to "ඡ", "CA" to "ඡැ", "CAa" to "ඡෑ",
        "Ci" to "ඡි", "Cii" to "ඡී", "Cu" to "ඡු", "Cuu" to "ඡූ",
        "Ce" to "ඡෙ", "Cee" to "ඡේ", "CY" to "ඡ්‍ය්", "CYa" to "ඡ්‍ය",
        "Co" to "ඡො", "Coo" to "ඡෝ", "CE" to "ඡෛ",
        "Cra" to "ඡ්‍ර", "CrA" to "ඡ්‍රැ", "CrAa" to "ඡ්‍රෑ",
        "Cri" to "ඡ්‍රි", "Crii" to "ඡ්‍රී", "Cru" to "ඡෘ",
        "Cruu" to "ඡෲ", "Cre" to "ඡ්‍රෙ", "Cree" to "ඡ්‍රේ",
        "Cro" to "ඡ්‍රො", "Croo" to "ඡ්‍රෝ", "CrE" to "ඡ්‍රෛ",
        "Cau" to "ඡෞ",

        // =========================
        // ඣ
        // =========================
        "J" to "ඣ්", "Ja" to "ඣ", "JA" to "ඣැ", "JAa" to "ඣෑ",
        "Ji" to "ඣි", "Jii" to "ඣී", "Ju" to "ඣු", "Juu" to "ඣූ",
        "Je" to "ඣෙ", "Jee" to "ඣේ", "JY" to "ඣ්‍ය්", "JYa" to "ඣ්‍ය",
        "Jo" to "ඣො", "Joo" to "ඣෝ", "JE" to "ඣෛ",
        "Jra" to "ඣ්‍ර", "JrA" to "ඣ්‍රැ", "JrAa" to "ඣ්‍රෑ",
        "Jri" to "ඣ්‍රි", "Jrii" to "ඣ්‍රී", "Jru" to "ඣෘ",
        "Jruu" to "ඣෲ", "Jre" to "ඣ්‍රෙ", "Jree" to "ඣ්‍රේ",
        "Jro" to "ඣ්‍රො", "Jroo" to "ඣ්‍රෝ", "JrE" to "ඣ්‍රෛ",
        "Jau" to "ඣෞ",

        // =========================
        // ට
        // =========================
        "Ta" to "ට", "TA" to "ටැ", "TAa" to "ටෑ",
        "Ti" to "ටි", "Tii" to "ටී", "Tu" to "ටු", "Tuu" to "ටූ",
        "Te" to "ටෙ", "Tee" to "ටේ", "TY" to "ට්‍ය්", "TYa" to "ට්‍ය",
        "To" to "ටො", "Too" to "ටෝ", "TE" to "ටෛ",
        "Tra" to "ට්‍ර", "TrA" to "ට්‍රැ", "TrAa" to "ට්‍රෑ",
        "Tri" to "ට්‍රි", "Trii" to "ට්‍රී", "Tru" to "ට්‍රෘ",
        "Truu" to "ට්‍රෲ", "Tre" to "ට්‍රෙ", "Tree" to "ට්‍රේ",
        "Tro" to "ට්‍රො", "Troo" to "ට්‍රෝ", "TrE" to "ට්‍රෛ",
        "Tau" to "ටෞ",

        // =========================
        // ඨ
        // =========================
        "Ttha" to "ඨ", "TthA" to "ඨැ", "TthAa" to "ඨෑ",
        "Tthi" to "ඨි", "Tthii" to "ඨී", "Tthu" to "ඨු",
        "Tthuu" to "ඨූ", "Tthe" to "ඨෙ", "Tthee" to "ඨේ",
        "TthY" to "ඨ්‍ය්", "TthYa" to "ඨ්‍ය",
        "Ttho" to "ඨො", "Tthoo" to "ඨෝ", "TthE" to "ඨෛ",
        "Tthra" to "ඨ්‍ර", "TthrA" to "ඨ්‍රැ", "TthrAa" to "ඨ්‍රෑ",
        "Tthri" to "ඨ්‍රි", "Tthrii" to "ඨ්‍රී", "Tthru" to "ඨෘ",
        "Tthruu" to "ඨෲ", "Tthre" to "ඨ්‍රෙ", "Tthree" to "ඨ්‍රේ",
        "Tthro" to "ඨ්‍රො", "Tthroo" to "ඨ්‍රෝ", "TthrE" to "ඨ්‍රෛ",
        "Tthau" to "ඨෞ",

        // =========================
        // ඩ
        // =========================
        "Da" to "ඩ", "DA" to "ඩැ", "DAa" to "ඩෑ",
        "Di" to "ඩි", "Dii" to "ඩී", "Du" to "ඩු", "Duu" to "ඩූ",
        "De" to "ඩෙ", "Dee" to "ඩේ", "DY" to "ඩ්‍ය්", "DYa" to "ඩ්‍ය",
        "Do" to "ඩො", "Doo" to "ඩෝ", "DE" to "ඩෛ",
        "Dra" to "ඩ්‍ර", "DrA" to "ඩ්‍රැ", "DrAa" to "ඩ්‍රෑ",
        "Dri" to "ඩ්‍රි", "Drii" to "ඩ්‍රී", "Dru" to "ඩෘ",
        "Druu" to "ඩෲ", "Dre" to "ඩ්‍රෙ", "Dree" to "ඩ්‍රේ",
        "Dro" to "ඩ්‍රො", "Droo" to "ඩ්‍රෝ", "DrE" to "ඩ්‍රෛ",
        "Dau" to "ඩෞ",

        // =========================
        // ඪ
        // =========================
        "Ddha" to "ඪ", "DdhA" to "ඪැ", "DdhAa" to "ඪෑ",
        "Ddhi" to "ඪි", "Ddhii" to "ඪී", "Ddhu" to "ඪු",
        "Ddhuu" to "ඪූ", "Ddhe" to "ඪෙ", "Ddhee" to "ඪේ",
        "DdhY" to "ඪ්‍ය්", "DdhYa" to "ඪ්‍ය",
        "Ddho" to "ඪො", "Ddhoo" to "ඪෝ", "DdhE" to "ඪෛ",
        "Ddhra" to "ඪ්‍ර", "DdhrA" to "ඪ්‍රැ", "DdhrAa" to "ඪ්‍රෑ",
        "Ddhri" to "ඪ්‍රි", "Dd hrii" to "ඪ්‍රී",
        "Ddhru" to "ඪෘ", "Ddhruu" to "ඪෲ",
        "Dd hre" to "ඪ්‍රෙ", "Ddhree" to "ඪ්‍රේ",
        "Ddhro" to "ඪ්‍රො", "Ddhroo" to "ඪ්‍රෝ",
        "DdhrE" to "ඪ්‍රෛ", "Ddhau" to "ඪෞ",

        // =========================
        // ථ
        // =========================
        "Tha" to "ථ", "ThA" to "ථැ", "ThAa" to "ථෑ",
        "Thi" to "ථි", "Thii" to "ථී", "Thu" to "ථු", "Thuu" to "ථූ",
        "The" to "ථෙ", "Thee" to "ථේ", "ThY" to "ථ්‍ය්", "ThYa" to "ථ්‍ය",
        "Tho" to "ථො", "Thoo" to "ථෝ", "ThE" to "ථෛ",
        "Thra" to "ථ්‍ර", "ThrA" to "ථ්‍රැ", "ThrAa" to "ථ්‍රෑ",
        "Thri" to "ථ්‍රි", "Thrii" to "ථ්‍රී", "Thru" to "ථෘ",
        "Thruu" to "ථෲ", "Thre" to "ථ්‍රෙ", "Three" to "ථ්‍රේ",
        "Thro" to "ථ්‍රො", "Throo" to "ථ්‍රෝ", "ThrE" to "ථ්‍රෛ",
        "Thau" to "ථෞ",

        // =========================
        // ධ
        // =========================
        "Dha" to "ධ", "DhA" to "ධැ", "DhAa" to "ධෑ",
        "Dhi" to "ධි", "Dhii" to "ධී", "Dhu" to "ධු", "Dhuu" to "ධූ",
        "Dhe" to "ධෙ", "Dhee" to "ධේ", "DhY" to "ධ්‍ය්", "DhYa" to "ධ්‍ය",
        "Dho" to "ධො", "Dhoo" to "ධෝ", "DhE" to "ධෛ",
        "Dhra" to "ධ්‍ර", "DhrA" to "ධ්‍රැ", "DhrAa" to "ධ්‍රෑ",
        "Dhri" to "ධ්‍රි", "Dhrii" to "ධ්‍රී", "Dhru" to "ධ්‍රෘ",
        "Dhruu" to "ධ්‍රෲ", "Dhre" to "ධ්‍රෙ", "Dhree" to "ධ්‍රේ",
        "Dhro" to "ධ්‍රො", "Dhroo" to "ධ්‍රෝ", "DhrE" to "ධ්‍රෛ",
        "Dhau" to "ධෞ",

        // =========================
        // ඵ
        // =========================
        "P" to "ඵ්", "Pa" to "ඵ", "PA" to "ඵැ", "PAa" to "ඵෑ",
        "Pi" to "ඵි", "Pii" to "ඵී", "Pu" to "ඵු", "Puu" to "ඵූ",
        "Pe" to "ඵෙ", "Pee" to "ඵේ", "PY" to "ඵ්‍ය්", "PYa" to "ඵ්‍ය",
        "Po" to "ඵො", "Poo" to "ඵෝ", "PE" to "ඵෛ",
        "Pra" to "ඵ්‍ර", "PrA" to "ඵ්‍රැ", "PrAa" to "ඵ්‍රෑ",
        "Pri" to "ඵ්‍රි", "Prii" to "ඵ්‍රී", "Pru" to "ඵෘ",
        "Pruu" to "ඵෲ", "Pre" to "ඵ්‍රෙ", "Pree" to "ඵ්‍රේ",
        "Pro" to "ඵ්‍රො", "Proo" to "ඵ්‍රෝ", "PrE" to "ඵ්‍රෛ",
        "Pau" to "ඵෞ",

        // =========================
        // භ
        // =========================
        "B" to "භ්", "Ba" to "භ", "BA" to "භැ", "BAa" to "භෑ",
        "Bi" to "භි", "Bii" to "භී", "Bu" to "භු", "Buu" to "භූ",
        "Be" to "භෙ", "Bee" to "භේ", "BY" to "භ්‍ය්", "BYa" to "භ්‍ය",
        "Bo" to "භො", "Boo" to "භෝ", "BE" to "භෛ",
        "Bra" to "භ්‍ර", "BrA" to "භ්‍රැ", "BrAa" to "භ්‍රෑ",
        "Bri" to "භ්‍රි", "Brii" to "භ්‍රී", "Bru" to "භෘ",
        "Bruu" to "භෲ", "Bre" to "භ්‍රෙ", "Bree" to "භ්‍රේ",
        "Bro" to "භ්‍රො", "Broo" to "භ්‍රෝ", "BrE" to "භ්‍රෛ",
        "Bau" to "භෞ",

        // =========================
        // ණ
        // =========================
        "N" to "ණ්", "Na" to "ණ", "NA" to "ණැ", "NAa" to "ණෑ",
        "Ni" to "ණි", "Nii" to "ණී", "Nu" to "ණු", "Nuu" to "ණූ",
        "Ne" to "ණෙ", "Nee" to "ණේ", "No" to "ණො", "Noo" to "ණෝ",
        "NE" to "ණෛ", "Nau" to "ණෞ",

        // =========================
        // ඤ
        // =========================
        "Ny" to "ඤ්", "Nya" to "ඤ", "NYA" to "ඤැ", "NYAa" to "ඤෑ",
        "Nyi" to "ඤි", "Nyii" to "ඤී", "Nyu" to "ඤු", "Nyuu" to "ඤූ",
        "Nye" to "ඤෙ", "Nyee" to "ඤේ", "Nyo" to "ඤො", "Nyoo" to "ඤෝ",
        "NyE" to "ඤෛ", "Nyau" to "ඤෞ",

        // =========================
        // ඥ
        // =========================
        "Jny" to "ඥ්", "Jnya" to "ඥ", "JnyA" to "ඥැ", "JnyAa" to "ඥෑ",
        "Jnyi" to "ඥි", "Jnyii" to "ඥී", "Jnyu" to "ඥු", "Jnyuu" to "ඥූ",
        "Jnye" to "ඥෙ", "Jnyee" to "ඥේ", "Jnyo" to "ඥො", "Jnyoo" to "ඥෝ",
        "JnyE" to "ඥෛ", "Jnyau" to "ඥෞ",

        // =========================
        // ළ
        // =========================
        "L" to "ළ්", "La" to "ළ", "LA" to "ළැ", "LAa" to "ළෑ",
        "Li" to "ළි", "Lii" to "ළී", "Lu" to "ළු", "Luu" to "ළූ",
        "Le" to "ළෙ", "Lee" to "ළේ", "Lo" to "ළො", "Loo" to "ළෝ",
        "LE" to "ළෛ", "Lau" to "ළෞ",

        // =========================
        // ශ
        // =========================
        "Sh" to "ශ්", "Sha" to "ශ", "SHA" to "ශැ", "SHAa" to "ශෑ",
        "Shi" to "ශි", "Shii" to "ශී", "Shu" to "ශු", "Shuu" to "ශූ",
        "She" to "ශෙ", "Shee" to "ශේ", "Sho" to "ශො", "Shoo" to "ශෝ",
        "ShE" to "ශෛ", "Shau" to "ශෞ",

        // =========================
        // ෂ
        // =========================
        "S" to "ෂ්", "Sa" to "ෂ", "SA" to "ෂැ", "SAa" to "ෂෑ",
        "Si" to "ෂි", "Sii" to "ෂී", "Su" to "ෂු", "Suu" to "ෂූ",
        "Se" to "ෂෙ", "See" to "ෂේ", "So" to "ෂො", "Soo" to "ෂෝ",
        "SE" to "ෂෛ", "Sau" to "ෂෞ",

        // =========================
        // ෆ
        // =========================
        "F" to "ෆ්", "Fa" to "ෆ", "FA" to "ෆැ", "FAa" to "ෆෑ",
        "Fi" to "ෆි", "Fii" to "ෆී", "Fu" to "ෆු", "Fuu" to "ෆූ",
        "Fe" to "ෆෙ", "Fee" to "ෆේ", "Fo" to "ෆො", "Foo" to "ෆෝ",
        "FE" to "ෆෛ", "Fau" to "ෆෞ",

        // =========================
        // ස්වර
        // =========================
        "a" to "අ",
        "aa" to "ආ",
        "A" to "ඇ",
        "Aa" to "ඈ",
        "i" to "ඉ",
        "ii" to "ඊ",
        "u" to "උ",
        "uu" to "ඌ",
        "e" to "එ",
        "ee" to "ඒ",
        "E" to "ඓ",
        "o" to "ඔ",
        "oo" to "ඕ",
        "O" to "ඖ",

        // =========================
        // ඍ / ඎ / ඏ / ඐ
        // =========================
        "Ru" to "ඍ",
        "Ruu" to "ඎ",
        "Lu" to "ඏ",
        "Luu" to "ඐ",

        // =========================
        // Extra
        // =========================
        "x" to "o"
    )

    fun translate(input: String): String {
        return mapping[input] ?: input
    }
}
