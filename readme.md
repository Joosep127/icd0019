Erindid

Tõstke kõik failid, mis on kataloogis src/exceptions oma Github'i 
reposse (icd0019) kataloogi src/exceptions.

Veenduge, et kood kompileerub.

Kui valite Idea-s Build menüüst -> Rebuild project peaks tulema teade 
"Compilation completed successfully ..."

1. Ülesande mõte on kirjutada kood, mis käitub korrektselt ka vea olukorras
   ja aru saada, kuidas sellist koodi testida.

   Kirjutage lõpuni klassis exceptions.basic.Code meetod readDataFrom().
   
   Meetod peab sissetuleva FakeFile tüüpi argumendi
   - avama (open)
   - sellest lugema (read)
   - ja selle sulgema (close)
   
   Kui lugemisel juhtub viga, peaks meetod tagastama "someDefaultValue" ja
   fail peab suletud saama.
   
   Koodi testimiseks on samas paketis asuv klass TryCatchSampleTests.java.
   Vea olukordade testimine päris faili abil on tülikas ja sellepärst on
   selles koodis faili simuleeritud. Testimise eesmärgil on loodud
   klass FakeFile, mis võimaldab meil oma koodi veaolukordi testida.

2. Klassis exceptions.basic.Code on meetod minimumElement(), mis leiab 
   massiivist väikseima elemendi.
   
   See, et meetod tagastab tühja sisendmassiivi puhul nulli, on halb lahendus.
   
   Muutke käitumist nii, et tühja massiivi või null-i puhul viskaks 
   meetod erindi (IllegalArgumentException).
   
   Kirjutage klassi MinimumElementTests test mõlema juhu kontrollimiseks.

3. Klassis exceptions.basic.Code on meetod containsSingleLetters().

   Selles meetodis on sobimatu try/catch konstruktsioon. Meetodis on ka viga, 
   mis avaldub, kui sisend on null.

   Tehke see containsSingleLetters() meetod korda. 
   
   Koodi testimiseks on klass BadTryCatchTest.java.
   
4. Selle ülesande mõte on näidata, kuidas kasutada erindeid info vahetuseks 
   programmi erinevate osade vahel.

   Paketis exceptions.channel on klass Program. See programm peaks arvutama 
   sisendi ja etteantud konfiguratsiooni põhjal tulemuse ja selle väljastama. 
   Praegu töötab programm vaid juhul, kui mingit viga ei tekki.

   Arvutuseks vajaliku konfiguratsiooni andmed tulevad klassist ConstantProvider. 
   Selles on meetod getMultiplier(), mis üldjuhul tagastab mingi konstandi.
   Antud juhul on konkreetne arvutus ebaoluline ja tähelepanu on veatöötlusel.
   
   Et kontrollida, kas meie kood töötab korrektselt ka vigade korral, on 
   ConstantProvider klassis tehtud lisameetodid, mis võimaldavad selle käitumist 
   juhtida. See käitumine on dokumenteeritud klassis ConstantProviderTests.
   
   Lisage programmile järgmine veahalduse loogika. 
   
   Kui ConstantProvider.getMultiplier() viskab erindi MissingConstantException,
   siis peaks programm väljastama "### Error: Constant is missing ###"
   Selle sõne saate meedodiga formatError("Constant is missing").
   
   Selle olukorra proovimiseks on klassis exceptions.ProgramTests meetod
   handlesMissingConstantException().   
   
   Kui ConstantProvider.getMultiplier() viskab erindi CorruptConfigurationException,
   siis peaks programm väljastama "### Error: Configuration file is corrupt ###"
   Selle sõne saate meedodiga formatError("Configuration file is corrupt").
   
   Kui ConstantProvider.getMultiplier() viskab erindi IOException,
   siis peaks programm väljastama "### Error: can't find configuration file ###"
   Selle sõne saate meedodiga formatError(e.getMessage()).
   
   Kontrollitud erindeid samal viisil simuleerida ei saa, seega viimase juhu 
   testimiseks peatete calculate() meetodis provider.getMultiplier() asemele 
   kirjutama provider.getMultiplier2();
   
5. Paketis exceptions.wrap on klass DirectoryListPrinter meetodiga printDirectoryList().
   
   Meetodi DirectoryListPrinter.getDirectoryFileList() deklaratsioonis on 
   märgitud "throws IOException" ja seega peab sama märke tegema ka 
   meetodistes, mis seda omakorda kasutavad.
   
   Kasutage meetodis getDirectoryFileList() erindite mähkimist (wrapping)
   selleks, et saaks kõigi meetodite deklaratsioonidest "throws IOException"
   eemaldada. 

6. Paketis exceptions.translate on klass ConfigurationLoader meetodiga 
   loadConfiguration(). Meedodi käivitamiseks on klass ConfigurationLoaderTests.

   Kui konfiguratsiooni lugemine ebaõnnestub teadatakse meile sellest erindiga.
   
   Soovime muuta programmi nii, et üldise erindi asemel visatakse 
   rakendusespetsiifiline erind.
   
   Kui konfiguratsiooni faili ei eksisteeri peaks programm viskama 
   erindi nimega ConfigurationException. Seda olukorda saate proovida määrates 
   konfiguratsioonifaili asukohaks "./non-existent".
   
   Kui konfiguratsiooni fail viitab faili asemel kataloogile peaks programm 
   viskama erinidi nimega InstallationException. Seda olukorda saate proovida 
   määrates konfiguratsioonifaili asukohaks "./".
   
7. Kirjutage programm, mis võtab sisse täisarvulise numbri ja tagastab 
   selle sõnadena. nt. 123 -> "ükssada kakskümmend kolm"
   
   Seda programmi võiks kasutada näiteks arvete automaatse genereerimise 
   juures.
   
   Soovime, et programm ei oleks seotud konkreetse keelega ja hiljem 
   oleks võimalik uusi keeli lisada.
   
   See paindlikus on lahendatud selliselt, et numbritega seotud sõnad on
   programmi juurde kuuluvates failides võti-väärtus paaridena.
   
   Näiteks:
       1=üks
       2=kaks
       teen=teist
       tens-suffix=kümmend

   Esialgu peate programmi tööle saama eesti ja inglise keeles ning ühes 
   kunstlikus keeles. Vastavad tõlke failid on ette antud.
   
   Kuna erinevates keeltes on numbreid tähistavate sõnade moodustamine 
   erinev, siis soovime selle erinevuse samuti tõlgetefaili abil lahendada.
   
   Näiteks eesti keeles on "üksteist" moodustatav sama reegli järgi, 
   nagu näiteks "kolmteist". Inglise keeles on "eleven" ebareeglipärane.
   
   Seega kui miski on ebareeglipärane, siis peab selle jaoks eraldi 
   tõlge olema. Kui programm mingi numbri jaoks tõlget ei leia, siis 
   moodustab ta selle üldise reegli alusel.
   
   nt. 11 = "üks" + "teist", 12 = "kaks" + "teist", jne.
   
   Programm peaks teatud vigadest teatama erindi viskamisega. Need 
   olukorrad on kirjeldatud programmi testides.

   Kvaliteetne programm on selline, mis on võimalikult paindlik ja 
   milles on võimalikult vähe duplikatsiooni.
   
   Keele failidest info lugemise näide on failis ReadPropertiesFileExample.java.
   
   Numbrite osadeks jagamise näide:

       int number = 231;
       int hundreds = number / 100; // jagamine täisarvu täpsusega
       number -= hundreds * 100;    // eemalda sajad
       int tens = number / 10;
       number -= tens * 10;
       int ones = number % 10;

   Kui teie kood oskab teisendada numbreid nullist kuni 130ni saate 4 punkti 
   ning kui see saab hakkama numbritega nullist kuni 1 miljardini, 
   siis saate 6 punkti.

   Selle ülesande kohta on ka koodi ülevaatuse võimalus. Kui esitate lahenduse 
   tähtajaks, siis tähtaja möödudes on teil võimalik kahe teise tudengi 
   tööd hinnata ja selle eest punkte saada. 
   
   Ülevaatuse leiate pärast tähtaja möödumist hindamissüsteemist ja selle 
   esitamiseks on aega 7 päeva.

   Ülevaatusel tuleb vastata kahele küsimusele.
     - Kas koodis on kohti, mis on seotud konkreetse keelega (Nt. eesti keelega)?
     - Kas kõik avatud ressursid saavad ka suletud?
   
8. Commit-ige muudatused ja push-ige need Bitbucket-isse. 
    
    Lisage commit-ile tag ex05.
    
    Veenduge tulemuste lehelt, et tulemus on positiivne.

Lahendused ja seletused 1-6 seletused 7: https://youtu.be/mpg2ccA_4AQ