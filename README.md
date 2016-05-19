# TESTIT2

Autorid: Marge Seppo, Mari-Ann Viljus
TESTIT2 on järgmine versioon programmist TESTIT (https://github.com/koodid/TESTIT).

TESTIT2 on programm, mis võimaldab luua ja hiljem kasutajal täita testi, mis on sisestatud tabeli kujul (csv formaadis) ja sisaldab valikvastustega piiramatul hulgal küsimusi. Kasutaja vastuste ja tabelis tulemustena sõnastatud alternatiivide "profiilide" (vastusevariandid konkreetse tulemuse lõikes) võrdlemisel tuuakse välja kasutaja jaoks parim või parimad tulemused. 

Tabelis formuleerituna on sisendiks:
- Lühike testi või küsitluse tutvustus kasutajale kuvamiseks.
- Küsimused või väited, mida esitatakse kasutajale. Võimalike küsimuste arv: 1 - palju.
- Küsimuste või väidete valikvastused. 
- Tulemuste alternatiivid (lõpp-tulemuseks kuvatavad testi vastused või tulemused). Võimalike tulemuste alternatiivide arv: 1 - kõik.
- Konkreetse tulemuse "õiged" vastused kõikidele küsimustele (kujuneb tulemuse "profiil").
- Tulemus(t)e kuvamisel esitatav lisainfo kõikide tulemuste alternatiivide kohta.

![sisendtabel](https://cloud.githubusercontent.com/assets/16835566/15314942/7197f4ae-1c1d-11e6-8c64-005bd2e7bfae.jpg)

Programmil on kaks poolt: testi loomine ja testi täitmine.
## Testi loomisel:
- Testi läbiviija ehk looja sisestab võtmesõna (minimaalselt 3 tähemärki), millega tema test seostatakse.
- Testi looja sisestab arvu, mis näitab kui suurt osa parimatest tulemustest (kõige rohkem ühtinud "profiilidest") selle testi puhul testi täitjale näidatakse.
- Enne faili sisestamist kontrollib programm, kas kõik vajalikud väljad on nõuetekohaselt täidetud ning ega ei leidu juba samasuguse koodiga testi. Viimasel juhul tuleb sisestada uus kood.
- Looja valib oma arvutist testi aluseks oleva tabeli.
- Programm kontrollib, kas kõik vajalikud väljad on täidetud.
- Kui programm ei leia info puudujääke, kuvatakse olulisem info (testi tutvustus, testi küsimused koos valikvastustega, testi tulemuste alternatiivid koos nende kohta käiva lisainfoga) loojale.
- Sisestatud failist tehakse koopia, millelt käivitub hiljem testi täitmine.
- Kui looja leiab, et kõik on sobiv, saab nupust "Valmis" programmi sulgeda. Kui millegipärast looja soovib kas testi muuta või selle loomisest loobuda, saab valida "Loobu ja kustuta", mis kustutab failist tehtud koopia (ja seega ka testi). Soovi korral saab kasutaja nüüd uue faili sisestada.

![testi_loomise_aken](https://cloud.githubusercontent.com/assets/16835566/15386956/319f4ac2-1db3-11e6-8e17-7b656f444656.jpg)

## Testi täitmisel:
- Testini jõudmiseks peab testi täitja sisestama kõigepealt testi loojalt saadud koodi või võtmesõna.
- Programm kontrollib, kas sellise koodiga fail on olemas. Kui faili pole, teavitatakse täitjat vigasest koodist. Kui fail on olemas, liigub programm edasi.
- Kuvab täitjale testi või küsitluse tutvustuse.
- Kuvab kõik tabelis olevad küsimused koos valikvastustega.
- Salvestab kasutaja vastused.
- Võrdleb kasutaja vastuseid iga alternatiivse tulemuse "profiiliga".
- Võrdluse põhjal kuvab kasutajale testi looja poolt määratud hulga tulemuste paremiku (nt. TOP 3, TOP 5 vmt) koos vastuste kattuvuse protsendiga.

![ligipaas](https://cloud.githubusercontent.com/assets/16835566/15315550/ef17a584-1c20-11e6-945a-8c3897b87e0b.jpg)
![testi_tutvustus](https://cloud.githubusercontent.com/assets/16835566/15315552/f551cd26-1c20-11e6-969a-2851be578198.jpg)
![kysimused](https://cloud.githubusercontent.com/assets/16835566/15315559/fba3fb0e-1c20-11e6-8343-4c672cf09dca.jpg)
![tulemused](https://cloud.githubusercontent.com/assets/16835566/15315561/fedc037a-1c20-11e6-89c1-467dd4b14e2e.jpg)

https://github.com/koodid/TESTIT2 
