# BilUdlejning
# Deployment til Heroku

1: Først logger vi ind på Heroku

2: Opretter et nyt projekt "New project", vælger region og giver det et navn

3: Man trykker på rescources og udner add-ons søger og trykker vi på Clear-DB da det er den online DB vi bruger.

4: Vi vælger den gratis version "Ignite" som ligger i toppen.

5: Tryk Submit order form.

6: Vi tjekker at Clear-DB er kommet på add-om listen og at den er til at åbne.

7: Vi går ind i settings, her trykker vi på Config-vars som åbner en undermenu.

8: Her er der et link under: CLEARDB_DATABASE_URL som vi udtrækker nogle værdier fra:

Brugernavn: jdbc: + det der er efter mqsql:// og før kolon

Password: efter kolon og før @

url: alt efter @

9: under config Vars tilføjer vi de tre nye linjer:

spring.datasource.username: brugernavnet

spring.datasource.password: passworded

spring.datasource.url: url

10: Med Workbench connecter vi til den online database vi har oprettet på ClearDB så vi kan lave et schema og tabeler.

11: Vi indtaster "ClearDB" i Connection name.

12: I hostname skrives "eu-cdbr-west-02.cleardb.net"

13: i username skrives brugernavnet

14: i default schema skrives enden af vores CLEAR_DATABASE_URL, så det starter med "heroku" og slutter lige inden "?".

15: Vi opretter de tables som vi har i database

16: Derefter tilretter vi vores connection, fra enviromental attributes til de attributes vi satte på Heroku.

17: inde på heroku trykker vi "Deploy" og logger ind med vores Github profil og under "Manual deploy" vælger vi vores deployment branch og trykker "Deploy".

18: Herefter er siden til at tilgå.
