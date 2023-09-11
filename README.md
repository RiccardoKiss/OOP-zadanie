# OOP-zadanie
***

# Predajňa stromov
**Java** projekt, s využitím framework-u **JavaFX** pre grafické rozhranie,  na ukážku princípov objektovo-orientovaného programovania.
Projekt bol vytvorený ako zadanie pre predmet "*Objektovo-orientované programovanie*" na [FIIT STU][fiit].

## Čo je spravené?
- javadoc komentáre a dokumentácia
- triedy rozdelené do balíkov
- abstraktné triedy
- dedenie (inheritance)
- enkapsulácia (atribúty tried su privátne, t.j. sú prístupné len cez getteri/setteri)
- preťažené konštruktory (overloading)
- prekonávané metódy (overriding)
- agregácia (aggregation)
- implementovaný návrhový vzor Observer medzi účtovníkom a firemným rozpočtom
- kompletne hotové grafické rozhranie (GUI)
- rozhrania (interface)
- výnimky (exception handling)
- spracovatelia udalostí (event handlers)
- viacniťovosť (multithreading)
- generickosť
- lambda výrazy (lambda expressions)
- implicitná implementácia metód v rozhraní (default method implementation)
- serializácia (serialization)

## Hierarchia tried
| **Zamestnanec:** | **Strom:** |
| ---------------- | ---------- |
|--> Vedúci | --> Listnatý
|--> Predavač | --> Ihličnatý
|--> Účtovník |
## Funkčnosť
Spustenie aplikácie z **Main.java** otvorí grafické rozhranie. Vytvorenie človeka je možné manuálne (zadávaním vlastných hodnôt) alebo aj automaticky náhodne vygenerovanými hodnotami. V prvom rade treba skontrolovať súbor **output.txt** v koreňovom priečinku projektu.
1. *Output.txt* **je** prázdny:
Prvá scéna slúži na vytvorenie vedúceho pre predajňu. Po vytvorení vedúceho nasleduje scéna na vytvorenie účtovníka. Po vytvorení účtovníka (ktorého najíma nami vytvorený vedúci z predošlej scény) sa ešte pred prepnutím do hlavnej scény uložia objekty **Veduci** a **Uctovnik** do textového súboru **output.txt**.
2. *Output.txt* **nie je** prázdny:
Keďže už máme vytvoreného vedúceho aj účtovníka z prvého spustenia aplikácie, nemusíme ich vytvárať znovu, ale rovno sa načítajú objekty z textového súboru output.txt. Hneď po spustení prvej scény na vytvorenie vedúceho môžeme rovno kliknúť na **„Ďalej“** a aplikácia preskočí scénu na vytvorenie účtovníka a prepne sa rovno do hlavnej scény.

#### Hlavná scéna:
V hlavnej scéne je najskôr potrebné vytvoriť predavačov a až následne potom zákazníkov. Nikdy nemôže byť väčší počet zákazníkov ako predavačov. Môžeme si to predstaviť tak, že aj v skutočnosti môže naraz každý predavač obslúžiť len jedného zákazníka. Ak chceme vytvoriť zákazníka, po ktorom by ich počet bol väčší ako počet predavačov (vypíše aj varovnú správu), tak musíme najskôr vytvoriť ďalšieho predavača.

Po každom vytvorení zákazníka si môžeme vybrať z ponuky stromov na nákup, ktoré si bude daný zákazník kupovať. Po vytvorení ďalšieho zákazníka už vyberáme nákup pre tohto aktuálne vytvoreného a k predchádzajúcim zákazníkom sa už nevieme vrátiť a tým pádom nevieme zmeniť už ich nákup.

Ak už nechceme ďalej nakupovať, tak po stlačení tlačidla **„STRET“** sa všetky peniaze, ktoré každý predavač zarobil od každého zákazníka prepošle každý predavač účtovníkovi, ktorý ich následne pričíta do firemného rozpočtu.

Zoznam zákazníkov sa po strete vymaže, ale zoznam predavačov zostáva a predaj stromov
môže ďalej pokračovať vytváraním nových zákazníkov a kupovaním stromov.

## Agregácia
V triede *Uctovnik* je vytvorená inštancia triedy *FiremnyRozpocet*. Každý účtovník dokáže pripočítavať alebo odčítavať z tohto **jedného** firemného účtu.
Uctovnik.java :
```java
import treeShop.predajna.FiremnyRozpocet;

public class Uctovnik extends Zamestnanec {
	private double peniaze;
	private FiremnyRozpocet fr = new FiremnyRozpocet(1000);
	...
}
```
FiremnyRozpocet.java :
```java
public class FiremnyRozpocet {
	private double rozpocet;
		
	public FiremnyRozpocet(double rozpocet) {
		this.rozpocet = rozpocet;
	}
	...
}
```
## Polymorfizmus
Napr. v triede nadtypu *Zamestnanec.java* je vytvorená metóda **info()**, ktorá je prekonaná v dcérskych triedach *Veduci.java*, *Uctovnik.java* a *Predavac.java*, takže je definovaná s novým telom v každej podtriede.
Zamestnanec.java :
```java
public String info() {
	return "";
}
```
Veduci.java :
```java
public String info() {
	return this.isPohlavie() ? 
			"[Vedúci]: " + this.getVek() + "-ročný " + this.getPohlavie() : 
			"[Vedúca]: " + this.getVek() + "-ročná " + this.getPohlavie();
}
```
Uctovnik.java :
```java
public String info() {
	return this.isPohlavie() ? 
			"[Účtovník]: " + this.getVek() + "-ročný " + this.getPohlavie() : 
			"[Účtovníčka]: " + this.getVek() + "-ročná " + this.getPohlavie();
}
```
Predavac.java :
```java
public String info() {
	return this.isPohlavie() ? 
			"[Predavač]: " + this.getVek() + "-ročný " + this.getPohlavie() : 
			"[Predavačka]: " + this.getVek() + "-ročná " + this.getPohlavie();
}
```
### Autor
 - **Kiss Riccardo** - GitHub: [RiccardoKiss][gitRK]

[fiit]: <https://www.fiit.stuba.sk/>
[gitRK]: <https://github.com/RiccardoKiss>
