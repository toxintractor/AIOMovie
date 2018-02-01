#Report

#####Description
De AIOMovie app laat de gebruiker een film zoeken. Daarna heeft de gebruiker de keuze om de film
gelijk te kopen of eerst via Torrent te downloaden. Men kan een lijst van films krijgen van films
die door Bol.com worden en een Lijst een Torrents van Yify.

#####Design

#####Challenges
De grootste "challanges" waren het parsen van de JSON van de TMDB en Yify API. Omdat de sites vaak 
crowdsourced zijn, ontbreekt er vaak informatie in de JSON objecten. Hier moet vaak rekening mee 
gehouden worden. De API's die hiervoor gebruikt heb waren altijd volledig en de responses waren
altijd consequent. 

Het verkrijgen van de juiste films in de Yify en Bol.com API was ook nog een uitdaging. Voor Yify 
heb ik dit opgelost de IMDB code te verkrijgen via de TMDB API. Hiervoor moest een extra request
gedaan worden. Dit gaat bijna altijd goed. Het komt zeer zelden voor dat de verkeerde films worden 
gevonden en getoond.

Verder wou ik de gebruiker laten zoeken op Jaar en Genres via een drop down menu.
Maar dit zou misschien een beetje onoverzichtelijk worden voor de gebruiker. Het zoeken op titel of 
keyword werkt ook heel goed.