#Process Book

### 15-01
Data van de TMDB en Yify binnenhalen via Volley. Ik heb volley niet gehad in periode 1. Daar deden we het met een httprequest.

### 16-01
De spinners werkend krijgen zodat men daarmee kan zoeken in de TMDB API

### 17-01
Lijst van TMDB results maken.

### 18-01
Storm! Een gekozen film moet gezocht kunnen worden via Yify. Ik moet er nog achterkomen hoe ik dit
het beste kan doen.

### 19-01
Vandaag heb ik de ativitys gemaakt voor de movieoption menu. Van de student assistenten heb ik als
tip gekregen om hier al de film informatie te verschaffen. Via deze activity kan men gerelateerde
films zoeken, reviews van de film opvragen, torrents opvragen of Bol.com producten zoeken.

### 22-01
De yify api gaat niet meer via veilige verbiding, dus ik krijg errors.
Om 11:00 een standup gehad met het team. Een paar handige tips gekregen, om mijn data in te laden.

### 23-01
De yify errors zijn er niet meer, ik moet kijken hoe ik dit kan omzeilen in de toekomst.
Vandaag ben ik er achter gekomen dat een film in de Yify API opgezocht kan worden via de IMDB code.

### 24-01
Vandaag de query opgezocht waarmee je de IMDB code kan opvragen via de TMDB API. Er is een apparte
request query voor. Dit moet gedaan worden voordat ik een torrent van een film kan vinden. Er kan
nu een film gezocht worden via de titel, en daarna de Torrents verkrijgen.

### 25-01
Vandaag goed gezocht hoe het beste een film gezocht kan worden in de Bol.com API. Er kan niet 
specifiek op films of videomateriaal gezocht worden. Ik heb er dus voor gekozen eerst op naam te 
zoeken. Hierna heb ik naar de atrubuten gekeken die je eruit krijgt. Er is een atribuut wat "GPC",
de gpc van films heeft als naam: "dvdmo". Ik heb hierop gefilterd.

### 26-01
De app begint eindelijk ergens op te lijken, hoewel er nog veel bugs in zitten.
Vandaag nagedacht over de search functie op de eerste activity. Ik kan nu alleen op titel zoeken,
maar ik wil een simpele app en ik heb niet extreem veel tijd. Vandaag moet ik een harde keuze maken.
Maar de keuze heb ik naar volgende week opgeschoven.

### 29-01
Vandaag de gekozen film data in de movieoptionactivity. Ik heb de naam, jaar, en een beschrijving
toegevoegd aan de pagina. Ook heb ik de acteurs erin gezet, dat gaat via een apparte request. Ook 
heb ik de reviewpagina uitgewerkt. Dit is een simpele pagina met reviews erin, dus ik er toch niet 
voor gekozen om een apparte review classe te maken om daarin infromatie op te slaan.

### 30-01
Vandaag begonnen met de layouts mooier te maken en de bugs eruit te halen. Ook heb ik de keuze 
gemaakt om alleen op titel of keyword te zoeken.

### 31-01
Vandaag verder gedebugt en de layout beter gemaakt. De is vrij stabiel alleen de platformen zijn 
vaak gecrowdsourced. Hierdoor zijn JSON responses vaak incompleet. De alles moet ik opvangen.

### 01-02
De app is erg stabiel, geeft geen errors of crasht niet. Vandaag heb ik het verslag en het filmpje
gemaakt. Ook heb ik mijn hele code doorgelopen om te kijken of er nog comments bij moeten.