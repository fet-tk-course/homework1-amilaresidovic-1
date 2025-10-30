**Zadaca A**

**Opis projekta**

Ovaj projekat prikazuje rad sa programerima i njihovim karakteristikama.
Omogućava spremanje podataka o backend i frontend developerima, prikaz njihovih jezika i frameworka,
analizu jezika koje koriste i filtriranje programera prema određenom frameworku.

**1**. **Struktura projekta**

_**Interface Osoba:**_ definiše osnovno ponašanje koje svaka osoba mora imati:
getImePrezime() vraća puno ime osobe, getZemlja() vraća oznaku zemlje.

**_Klasa Programer:_**:osnovna klasa koja implementira interface Osoba i sadrži:     
_`Osnovni podaci`_: ime, prezime, godine iskustva, oznaka zemlje, programski jezici  
`Validacija podatka`: u init bloku se provjerava da ime ne smije biti prazno,
broj godina ne smije biti negativan i lista jezika ne smije biti prazna  
`Normalizacija jezika`: svi nazivi se pretvaraju u mala slova uz lowercase()

**Izvedene klase:** obje klase nasljeđuju Programer i dodaju specifične informacije:
BackendDeveloper sadrži dodatno polje backendFramework, a
FrontendDeveloper sadrži dodatno polje frontendFramework

**2. Kako pokrenuti program**

* Projekat se otvori u IntelliJ IDEA
* Otvori se Main.kt i pokrene sa Run
* Rezultati se prikažu u konzoli

**3. Operacije nad podacima**

**`Analiza jezika:`**: broji koliko programera koristi svaki jezik

* Verzija 1  
  Korištene funkcije: flatMap za spajanje svih lista jezika u jednu listu, groupingBy grupiše jezike i eachCount() broji ponavljanja

* Verzija 2  
  Koristi mutableMapOf za čuvanje brojača, getOrDefault(j, 0) se koristi za sigurno dohvaćanje vrijednosti

**`Prosjecno iskustvo`**: računa prosječne godine iskustva po jeziku

* Verzija 1:  
  Kreira parove (jezik, godine), grupiše ih po jeziku i uz mapValues i average() računa prosjek

* Verzija 2:  
  Koristi mapu za sumu koja će čuvati ukupan broj godina iskustva i mapu za čuvanje broja programera

**3. Filtriranje po frameworku**

Pronalazi sve programere koji koriste određeni framework.
Ako je objekat BackendDeveloper i njegov backendFramework odgovara traženom, taj programer se dodaje u listu.
Isto važi i za FrontendDeveloper.
Funkcija vraća listu svih programera koji koriste određeni framework.

**4. Ispis**

Za ispis podataka koristi se if/else uslov kako bi se provjerilo da li je backend ili frontend developer,
i na osnovu toga ispisuje se tekst koji sadrži ime i prezime, listu programskih jezika
i framework koji koristi.

**5. Uporedna analiza pristupa**

* Verzija 1: Kraći i čitljiviji kod, manje mogućnosti za greške, ali može biti teže za razumijevanje

* Verzija 2: Eksplicitna kontrola toka, lakše je pratiti korak po korak, ali je mana što ima više koda

**6. Upotreba AI alata**

Koristila sam ChatGPT za razumijevanje kako kreirati parove podataka
i kako koristiti mapValues sa average() za računanje prosjeka iz grupiranih podataka.  
Prompt koji sam koristila: Kako mogu u kotlinu povezati svaki programski jezik sa godinama iskustva programera
i nakon grupisanja izračunati prosjek?


**7. Primjeri rezultata programa**

`Validacija podataka`  
Provjera 1: Ime ne smije biti prazno  
Provjera 2: Broj godina ne smije biti negativan  
Provjera 3: Lista jezika ne smije biti prazna

`Prikaz svih programera`  
Amila Residovic - Backend developer - jezici: kotlin, java - framework: Spring Boot  
Emina Jusufovic - Backend developer - jezici: kotlin, python - framework: Spring Boot  
Amina Hasic - Backend developer - jezici: java, python - framework: Ktor  
Ajla Arnaut - Frontend developer - jezici: javascript, typescript - framework: React  
Maid Idrizovic - Frontend developer - jezici: javascript, html - framework: Vue.js

`Analiza jezika: Verzija 1`  
kotlin: 2 programera  
java: 2 programera  
python: 2 programera  
javascript: 2 programera  
typescript: 1 programera  
html: 1 programera

`Analiza jezika: Verzija 2`  
kotlin: 2 programera   
java: 2 programera  
python: 2 programera  
javascript: 2 programera  
typescript: 1 programera  
html: 1 programera

`Prosjecno iskustvo: Verzija 1`  
kotlin: 3.5 godina  
java: 2.5 godina  
python: 4.0 godina  
javascript: 5.0 godina  
typescript: 4.0 godina  
html: 6.0 godina

`Prosjecno iskustvo: Verzija 2`  
kotlin: 3.5 godina  
java: 2.5 godina  
python: 4.0 godina  
javascript: 5.0 godina  
typescript: 4.0 godina  
html: 6.0 godina

`Filtriranje za Spring Boot`  
Amila Residovic - Backend developer - jezici: kotlin, java - framework: Spring Boot  
Emina Jusufovic - Backend developer - jezici: kotlin, python - framework: Spring Boot  