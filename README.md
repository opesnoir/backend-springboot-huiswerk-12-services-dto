# backend-springboot-huiswerk-12-services-dto

Belangrijk

Na deze les moeten de requestMappings in de controller de juiste responses geven.
Na deze les is de applicatie voor het eerst functioneel.
Het is belangrijk om goed te begrijpen hoe de lagen in onze multi-tier application samenwerken:

De Controller ontvangt een verzoek op een endpoint, als er variable worden meegeven aan dit verzoek komen die binnen via de TelevisionInputDto ->
De Controller geeft aan de hand van het verzoek en eventuele meegekregen variable dit door aan de Service ->
De Service spreekt aan de hand van de geschreven functie de Repository aan met de juiste find functie/query ->
De Repository gaat aan de hand van het bijbehorende Model zoeken naar de juiste gegevens en stuurt deze terug naar de Service ->
De Service past de logica toe uit de functie en geeft de response aan de hand van de TelevisionDto terug aan de Controller (De controller wordt vanuit de front-end of vanuit Postman aangesproken, en geeft ook het antwoord weer terug.)
