# Report

### Description
De AIOMovie app laat de gebruiker een film zoeken. Daarna heeft de gebruiker de keuze om de film
gelijk te kopen of eerst via Torrent te downloaden. Men kan een lijst van films krijgen van films
die door Bol.com worden en een Lijst een Torrents van Yify.

### Design

###### First design
<img src="https://github.com/toxintractor/AIOMovie/blob/master/doc/advanced%20sketch.png?raw=true">
<img src="https://github.com/toxintractor/AIOMovie/blob/master/doc/diagrams.png?raw=true">

###### Final design
<img src="https://github.com/toxintractor/AIOMovie/blob/master/doc/finaladvacnedsketch.png?raw=true">


### Challenges
De grootste "challanges" waren het parsen van de JSON van de TMDB en Yify API. Omdat de sites vaak 
crowdsourced zijn, ontbreekt er vaak informatie in de JSON objecten. Hier moet vaak rekening mee 
gehouden worden. De API's die hiervoor gebruikt heb waren altijd volledig en de responses waren
altijd consequent. 

Het verkrijgen van de juiste films in de Yify en Bol.com API was ook nog een uitdaging. Voor Yify 
heb ik dit opgelost de IMDB code te verkrijgen via de TMDB API. Hiervoor moest een extra request
gedaan worden. Dit gaat bijna altijd goed. Het komt zeer zelden voor dat de verkeerde films worden 
gevonden en getoond. Via de Bol.com API gaat dit wat lastiger. Het zoeken via API naar specifieke 
producten gaat vrij lastig. Ook heb je verschillende soorten paketten en edities waarin een film
zit. Daarom krijgt men een lijst met films. Hierin zitten verschillende soorten edities en
formaten zoals Blue-Ray, dvd en 3D Blue-Ray. Men kan bijna altijd wel de juiste film in een 
geschikt formaat vinden.

Verder wou ik de gebruiker laten zoeken op Jaar en Genres via een drop down menu.
Maar dit zou misschien een beetje onoverzichtelijk worden voor de gebruiker. Het zoeken op titel of 
keyword werkt ook heel goed.

Een andere uitdaging was het visueel design van mijn app. Ik ben hier niet zo goed in. Mijn app
zal waarschijnlijk niet de mooiste zijn. Wel wou ik de app er simpel en overzichtelijk 
uit laten zien. Dit is naar mijn mening vrij goed gelukt.

### Decisions
Een aantal veranderingen zijn zoals eerder genoemd de zoekfunctie in de eerste activity. De 
versimpelde zoektechniek werkt naam mijn mening wat simpeler. Ook heb ik geen apparte activity 
gemaakt voor informatie over de film. De belangrijke informatie vind je in resultaten lijst. 
In de resultaten lijst vind men de titel, jaar en de beoordeling van de film. Als men een film kiest
ziet hij dan weer de titel, het jaar, een korte bescrhijving en maximaal vijf acteurs. Dit advies
kreeg ik ook tijdens de standups en van de assistenten. Verder heb ik de beoordeling van de films
niet gerepresenteerd met sterren, maar gewoon met een cijfer. Door tijdnood heb ik dit niet kunnen
doen.