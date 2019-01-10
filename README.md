visit our [GitHub Pages](https://sweihm-ws2018-19.github.io/skillproject-fr-22/)!

## Soup-IT

Beta Test für SoupIT (Invite Link):
https://skills-store.amazon.com/deeplink/tvt/28475fb1a61e0a449fda68ae373fc150b6d5be47c812708c42526791f5895976fe3122c1f8b97acef8b99fbbfb9ca5287e76bf4c203fbb8a7c0eeaf0fbd2f50251c99ff461e7dd9e2d0b499ef8ecf94c5bcaea41bf33b3cab0baa1f18f97428a98a865e5cb4a00089430372d4fc37dc0

Soup-IT ist eine Anwendung, die den Nutzer bei der Zubereitung von Suppen unterstützen soll. 

Das System sieht vor, dass der Nutzer eine Liste von Zutaten vorschlägt, welche ihm zur Verfügung stehen. Auf Basis dieser Informationen soll Alexa eine Liste mit möglichen Rezepten ausgeben, welche die größten Übereinstimmungen mit der gegebenen Zutatenliste haben.

Werden keine Zutaten des Nutzers vorgegeben, erfolgt kein Rezeptvorschlag. 

Zudem soll es dem Nutzer ermöglicht werden seine Essens-Präferenzen zu spezifizieren. 

Nach der Wahl eines Rezeptes und Angabe der gewünschten Portionen werden alle benötigten Zutaten mit Menge ausgegeben.

Sollte dem Nutzer für das gewählte Rezept eine Zutat fehlen, so soll es möglich sein, das Rezept zu wechseln oder die fehlende Zutat zur Alexa Einkaufsliste hinzuzufügen und mit dem Rezept zu einem späteren Zeitpunkt fortzufahren. 

Anderenfalls beginnt die Anwendung den Nutzer Schritt-für-Schritt durch den Kochprozess zu begleiten. Der Nutzer kann den Ausgabefluss der Rezeptzubereitung selbst mit „weiter“ und „zurück“ steuern. 

### Hauptziele und Zielgruppen 

Unser Ziel besteht darin, das Kochen von Rezepten per Spracheingabe zu ermöglichen.

Mehrmaliges Durchlesen der einzelnen Kochschritte sowie die Bedienung eines Touchscreens mit „schmutzigen Fingern“ während dem Kochen werden überflüssig. 

Die Anwendung soll Kochanfängern das Kochen durch speziell formulierte Anleitungen erleichtern sowie beschäftigte Berufstätige oder Studenten mit wenig Zeit zum Kochen von leckeren Suppen in der Freizeit animieren. Daher wird ein großer Wert auf möglichst einfache und schnelle Rezepte, die zugleich auch lecker und gesund sind, gelegt.

Als Vision stellen wir uns die Benutzung dieser Anwendung in verschiedensten Lebenslagen bzw. Altersklassen vor.

Was zunächst nur für Suppen möglich sein soll, könnte in Zukunft auch für verschiedene andere Gerichte angewandt werden. 

Auch Hobby-Köchen mit mehr Erfahrung soll die Anwendung eine interessante Alternative für das „Kochen nach Rezept“ bieten. Denn mit einem persönlichen Kochassistenten macht das Kochen sicherlich noch mehr Spaß als alleine.

## Anwendungsfalldiagramm

![anwendungsfall](https://user-images.githubusercontent.com/43879315/48561755-f2512780-e8f0-11e8-8f2c-ce7bfd377475.png)


## User Stories

Name der User Story	User Story
Okay	Wenn Okay gesagt wird, kommt der nächste Schritt

Zutatenliste nennen
	Der Akteur sagt Alexa seine vorhandenen Zutaten. Diese werden in einer Liste gespeichert, auch ihre Menge ist wichtig. Z.B. Ich habe 3 Karotten und 192 Kartoffeln

Rezeptvorschläge bekommen
	Der Akteur bekommt Rezepte ausgegeben, die zu seinen vorhandenen Zutaten passen. Z.B hat er Kartoffeln und daher wird unter anderem die Kartoffelsuppe für ihn ausgegeben

Rezept auswählen
	Der Akteur wählt sein ausgewähltes Rezept aus einer Liste von Rezepten aus. Das Rezept wird gespeichert. Z.B Kartoffelsuppe

Zutaten auf Einkaufsliste setzen
	Der Akteur kann Zutaten auf seine in Alexa schon integrierte Einkaufsliste setzen. Zum Beispiel "Setz Karotten auf die Einkaufsliste"

Zubereitung beginnen
	Man beginnt die Zubereitung des ausgewählten Rezepts

Zubereitung abbrechen
	Der Akteur kann die Zubereitung komplett abrechen

Hilfe
	Der Akteur bekommt gesagt was seine derzeitigen Optionen sind z.B. Schritt wiederholen oder weiter

PauseIntent
	Der User kann SoupIT pausieren

Audio Datei	Alexa soll am Anfang und Ende einen Ton abspielen

RestartIntent	Alexa soll vom Nutzer aus und wieder an-geschaltet werden können auch nachdem dieser ein halbe Stunde weg war 

Kein Rezept gefunden	Alexa sagt nur dass sie kein Rezept gefunden hat, wenn der User dabei ist Zutaten zu nennen

Zufall	Es werden von Alexa zufällige Texte für die gleichen Schritte angegeben um es für den User abwechslungsreicher zu machen

Wiederholung	Man kann das zuletzt gesagte von Alexa wiederholen lassen

Synonyme	Alexa erkennt Synonyme für Zutaten z.B. Möhre und Karotte

Plural	Alexa gibt den Plural jeder Zutat grammatikalisch korrekt wieder

Brüche	Alexa gibt Brüche grammatikalisch korrekt wieder

Portionen	Der User kann wählen wieviel Portionen er haben will

Wiedergabe	Nur maximal sechs passende Rezepte werden wiedergegeben in absteigender Übereinstimmung

Zutaten wiederholen	Die Zutaten eines Rezeptes sollen wiederholt werden können

Inspiration durch Alexa	Alexa soll dem User Sachen vorschlagen können, wenn dieser mal neue Rezepte ausprobieren will

Rezepte anpassen
	Der User soll Rezepte fürs nächste Mal anpassen können z.B. bei einer Erdnussallergie
Einzelne Schritte	Die Zubereitung wird in einzelnen Schritten angegeben damit der User langsam mitkochen kann

Zutaten verwenden, die da sind	Der User kann von basierend auf Zutaten, die er im Kühlschrank hat Rezeptvorschläge bekommen

