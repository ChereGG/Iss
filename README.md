BIBLIOTECA

O biblioteca contine o lista de carti si o lista de abonati ai bibliotecii care au drept de imprumut asupra cartilor. Pentru fiecare abonat se va retine un ID de inregistrare, Nume, Prenume,
	adresa,telefon si data expirarii abonamentului la biblioteca.
Cartile pot exista in mai multe exemplare , o carte se va retine prin: ID, Titlu, Autor, Editura, si Data Publicarii
O inchiriere are loc intre un abonat si biblioteca pentru o carte. O inchiriere se va reprezenta prin ID-ul iinchirierii si cate un ID pentru utilizator si cartea inchiriata
	si un atribut care marcheaza inregistrarea drept restituita sau nu.
O inchiriere leaga un client de O CARTE. Pentru fiecare carte inchiriata avem o noua inregistrare de inchiriere (pentru a facilita returnarea cartilor in cazul in care 
	abonatul nu finalizeaza citirea tuturor cartilor simultan.
Restituirea are loc prin modificarea atributului anumitei inchirierii la RESTITUIT. 
In momentul unei inchirieri/restituiri  toti clientii vor vedea lista actualizata a cartilor disponibile.
Contul bibliotecarului va oferi un "LOG" cu toate inchirierile si restituririle.
Abonatul va vedea in fiecare moment cat timp mai are pana la finalizarea perioadei de inchiriere. Daca aceasta data este depasita bibliotecarul va penaliza utilizatorul cu imposibilitatea
de a mai inchiria carti pentru urmatoarele 7 zile
