# TKT10004, Tietokantojen perusteet, 5 op, syksy 2017

Tietokantojen perusteet -kurssilla ryhmätyönä tehtävä web-sovellus.

Ryhmän jäsenet:
- Johannes Hyry
- Timo Järvenpää
- Jouko Kelomäki
- Mikko Savolainen
- Aleksi Willman


Kuvaus sovelluksesta:

Sovelluksella voi tallentaa tietokantaan reseptejä. Jokaisen annoksen reseptiin liittyy raaka-aine ja raaka-ainekohtainen määrä ja ohje.


Tietokanta:

Luodut taulut ovat:

"CREATE TABLE Annos (id integer PRIMARY KEY, nimi varchar(255));"
"CREATE TABLE RaakaAine (id integer PRIMARY KEY, nimi varchar(255));"
"CREATE TABLE AnnosRaakaAine "
+ "(raaka_aine_id integer,"
+ " annos_id integer,"
+ " maara varchar(100),"
+ " ohje varchar(300),"
+ " FOREIGN KEY (raaka_aine_id) REFERENCES RaakaAine(id),"
+ " FOREIGN KEY (annos_id) REFERENCES Annos(id));"







