# sna-transportnetwork

=== Daten Transformieren für Gephi

* Im Ordner "Resources" muss ein Unterordner mit dem Name "data" angelegt werden
* Alle Files, die im Data-Ordner liegen, werden eingelesen und zu einer einzigen CSV-Datei mit durchschnittlichen Werten verarbeitet. Im Data-Ordner muss mindenstens ein CSV-File des Typs "Soll/Ist Vergleich Abfahrts-/Ankunftszeiten SBB" liegen: https://data.sbb.ch/explore/dataset/ist-daten-sbb/export/ 
* Im Resources-Ordner muss das CSV-File mit den Geolocations liegen, dies wird in der Main-Methode mit dem Filenamen angegeben. 
* In der Main-Methode können die Bezeichnung für die beiden Output-Files angepasst werden:
** Bezeichnung für das CSV-File für die Kanten: application.exportConnectionsToCSV(<Filename as String.csv>);
** Bezeichnung für das CSV-File für die Knoten: application.trainStationsToNodesList(<Filename as String.csv>);

* Die Applikation wird mit Start der Main-Methode ausgeführt

