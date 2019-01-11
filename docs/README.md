# <img src="https://user-images.githubusercontent.com/43878732/51005877-f5f3da00-1540-11e9-9db0-08cf20d5f70a.png" width="40">  Soup-IT    

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


### Anwendungsbeispiel


- Alexa, öffne Soup IT.

- *Willkommen bei Soup it. Als dein persönlicher Assistent begleite ich dich bei der Suppenzubereitung. Welche Zutaten möchtest du verwenden?*

- Ich habe Tomaten, Zwiebeln und Sahne.

- *Ich kann dir anhand der genannten Zutaten vier Rezepte vorschlagen: Tomatencremesuppe, Gemüsecremesuppe und zwei weitere. Wähle eine Suppe oder sage: Weitere anhören*

- Ich möchte die Tomatencremesuppe kochen.

- *Wie viele Portionen möchtest du kochen?*

- Zwei Portionen.

- *Für eine Tomatencremesuppe für zwei Portionen benötigst du die folgenden vier Zutaten: dreihundertfünfzig Gramm Tomaten, eine halbe Zwiebel, zweihundertfünfzig milliliter Gemüsebrühe und fünfzig Milliliter Sahne. Hast du alle Zutaten vorrätig?*

- Ja.

- *Super. Lass uns mit der Zubereitung beginnen. Schäle die Zwiebel und schneide sie klein.*

- Weiter.

- *Gebe alle Zutaten in den Topf und lasse sie zehn Minuten köcheln.*

- Weiter.

- *Ich hoffe die Suppe schmeckt und wünsche einen guten Appetit.*

### Anwendungsfalldiagramm

![untitled diagram 1](https://user-images.githubusercontent.com/43779031/51003773-57fc1180-1538-11e9-9904-7199c7e94d01.png)


### User Stories

Name   der User Story | User   Story
-- | --
Audio Datei | Alexa soll am Anfang und Ende einen Ton abspielen.
Zutaten verwenden, die da sind | Der User kann von basierend auf Zutaten, die er   im Kühlschrank hat Rezeptvorschläge bekommen.
Inspiration durch Alexa | Alexa soll dem User alternativ auch Rezepte vorschlagen können, wenn dieser sich inspirieren lassen möchte anstatt Zutaten zu nennen.
Zutatenliste nennen | Der Akteur sagt Alexa seine vorhandenen Zutaten. Diese werden in einer Liste gespeichert.
Rezeptvorschläge bekommen | Der Akteur bekommt Rezepte ausgegeben, die   zu seinen vorhandenen Zutaten passen. 
Wiedergabe | Es werden nie mehr als 3 Rezepte auf einmal vorgelesen und insgesamt maximal 6 vorgeschlagen. Die Rezepte werden in absteigender Reihenfolge nach Übereinstimmungen von Zutaten sortiert.
Rezept auswählen | Der Akteur wählt sein ausgewähltes Rezept aus einer Liste von Rezepten aus. Das Rezept wird gespeichert.
Portionen | Der User kann wählen wieviele Portionen er kochen möchte.
Zutaten auf Einkaufsliste setzen | Der Akteur kann Zutaten auf seine in Alexa   schon integrierte Einkaufsliste setzen. Zum Beispiel "Setze Karotten auf   die Einkaufsliste". 
Zubereitung beginnen | Man beginnt die Zubereitung des   ausgewählten Rezepts.
Zubereitung abbrechen | Der Akteur kann die Zubereitung komplett abrechen.
Einzelne Schritte | Die Zubereitung wird in einzelnen Schritten   angegeben damit der User langsam mitkochen kann.
Zutaten wiederholen | Die Zutaten eines Rezeptes sollen jederzeit wiederholt werden können. Z.b. "Wie viele Eier brauche ich nochmal?".
Okay | Man kann statt "Ja" auch "Okay" sagen. Wenn Okay gesagt wird, kommt der nächste Schritt.
PauseIntent | Der User kann SoupIT jederzeit pausieren.
RestartIntent | Alexa setzt genau dort wieder an, wo der User pausiert hat. Gespeicherte Informationen sind noch erhalten.
Kein Rezept gefunden | Alexa sagt nur dass sie kein Rezept gefunden hat, wenn der User grade dabei ist Zutaten zu nennen.
Zufall | Es werden von Alexa zufällige Texte für die gleichen Schritte angegeben um es für den User abwechslungsreicher zu machen.
Wiederholung | Man kann jederzeit das zuletzt gesagte von Alexa wiederholen lassen.
Synonyme | Alexa erkennt Synonyme für Zutaten z.B.   Möhre und Karotte und schlägt einem Rezepte vor, die das Synonym enthalten. Genauso bekommt man wenn man "Fisch" sagt alle Rezepte die irgendeinen Fisch, z. B. Lachs, enthalten. 
Plural | Alexa gibt den Plural jeder Zutat grammatikalisch korrekt wieder.
Brüche | Alexa gibt Brüche grammatikalisch korrekt wieder.
Hilfe | Der Akteur bekommt gesagt was seine derzeitigen Optionen sind z.B. Schritt wiederholen oder weiter.

### Fachklassendiagramm

![fachklassen_fertig](https://user-images.githubusercontent.com/43878732/51002600-14070d80-1534-11e9-8969-b0d434b9feb9.png)

Für eine bessere Ansicht, sehen Sie sich das [Fachklassendiagramm im Vollbild](https://user-images.githubusercontent.com/43878732/51002600-14070d80-1534-11e9-8969-b0d434b9feb9.png) an.


### Sequenz-Diagramme

![1 1](https://user-images.githubusercontent.com/43878732/49977022-d6b75c00-ff44-11e8-93e8-2b6791a2f585.png)

[begrüßung](https://user-images.githubusercontent.com/43878732/49977022-d6b75c00-ff44-11e8-93e8-2b6791a2f585.png)

![6weitereezepte](https://user-images.githubusercontent.com/43878732/49977117-58a78500-ff45-11e8-8dd2-31d84a6eb889.png)

[weitereezepte](https://user-images.githubusercontent.com/43878732/49977117-58a78500-ff45-11e8-8dd2-31d84a6eb889.png)

![1rezeptwahlen](https://user-images.githubusercontent.com/43878732/49977184-9efce400-ff45-11e8-90b8-0bdeecc43974.png)

[rezeptwählen](https://user-images.githubusercontent.com/43878732/49977184-9efce400-ff45-11e8-90b8-0bdeecc43974.png)

![1rezept 1](https://user-images.githubusercontent.com/43878732/49977469-bdafaa80-ff46-11e8-80e9-936390e3092e.png)

[rezept](https://user-images.githubusercontent.com/43878732/49977469-bdafaa80-ff46-11e8-80e9-936390e3092e.png)

![1portionen 1](https://user-images.githubusercontent.com/43878732/49977775-eedcaa80-ff47-11e8-9899-4f66e3bdac76.png)

[portionen](https://user-images.githubusercontent.com/43878732/49977775-eedcaa80-ff47-11e8-9899-4f66e3bdac76.png)

![1beenden](https://user-images.githubusercontent.com/43878732/49977660-7b3a9d80-ff47-11e8-940d-b2aea765e211.png)

[beenden](https://user-images.githubusercontent.com/43878732/49977660-7b3a9d80-ff47-11e8-940d-b2aea765e211.png)


### Status

![Build Status](https://travis-ci.org/sweIhm-ws2018-19/skillproject-fr-22.svg?branch=master) ![Build Status](https://sonarcloud.io/api/project_badges/measure?project=alexa-skills-kit-samples%3Asoupit&metric=alert_status)


### Beta Test für SoupIT (Invite Link)

[Invite Link](https://skills-store.amazon.com/deeplink/tvt/28475fb1a61e0a449fda68ae373fc150b6d5be47c812708c42526791f5895976fe3122c1f8b97acef8b99fbbfb9ca5287e76bf4c203fbb8a7c0eeaf0fbd2f50251c99ff461e7dd9e2d0b499ef8ecf94c5bcaea41bf33b3cab0baa1f18f97428a98a865e5cb4a00089430372d4fc37dc0)




