# Sogeti Car Lease REST API

De **Sogeti Car Lease REST API** is een Java Spring Boot-toepassing waarmee auto's, klanten en contracten beheerd kunnen worden. Deze API biedt endpoints voor het uitvoeren van CRUD-operaties op auto's, klanten en contracten.

## Functionaliteiten

- **Auto's beheren**: Voeg nieuwe auto's toe, bekijk ze, werk ze bij of verwijder ze.
- **Klanten beheren**: Voeg klanten toe, bekijk gegevens en verwijder klanten.
- **Contracten beheren**: Maak contracten aan, koppel auto's aan klanten en beheer contractperiodes.

---

## Installatie

### 1. Vereisten

Zorg ervoor dat je de volgende tools geïnstalleerd hebt:
- **Java 17** of hoger
- **Maven**

### 2. Clone de repository

```bash
git clone https://github.com/jouw-gebruikersnaam/carlease-api.git
cd carlease-api
```

### 3. Build en start de applicatie

Gebruik Maven om de applicatie te bouwen en te starten:

```bash
mvn clean install
mvn spring-boot:run
```

De applicatie draait nu op `http://localhost:8080`.

---

## Endpoints

## Gebruik
Alle  API functies zijn beschikbaar via Swagger: http://localhost:8080/swagger-ui/index.html


### Testen met Swagger

1. Open [Swagger](http://localhost:8080/swagger-ui/index.html)
2. Registreer een nieuwe gebruiker waarmee je toegang krijgt tot in de API
```bash
curl -H 'Content-Type: application/json' -X POST -d '{"username": "admin", "password": "admin"}' http://localhost:8080/api/register
```
3.Login met de zojuist geregistreerde gebruiker
```bash
curl -H 'Content-Type: application/json' -X POST -d '{"username": "admin", "password": "admin"}' http://localhost:8080/api/login
```
4. Bij een successvolle Login ontvang je een JWT token.
5. Voer het JWT token in via de knop "Authorize".
6. 8. Stel de headers in:
   - **Content-Type:** `application/json`
7. Voeg een auto toe aan de database
8. Voeg een klant toe aan de database
9. Koppel een klant aan een auto middels een contract

### Curl

Je kunt ook curl gebruiken om de API te testen. Voorbeeld:

Registreer een nieuwe gebruiker waarmee je toegang krijgt tot in de API
```bash
curl -H 'Content-Type: application/json' -X POST -d '{"username": "admin", "password": "admin"}' http://localhost:8080/api/register
```
Login met de zojuist geregistreerde gebruiker
```bash
curl -H 'Content-Type: application/json' -X POST -d '{"username": "admin", "password": "admin"}' http://localhost:8080/api/login
```

Bij een successvolle Login ontvang je een JWT token gebruik deze token in alle volgende commando's. Vervang de voorbeeld token door jouw eigen token.
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMzkyODc4MywiZXhwIjoxNzMzOTY0NzgzfQ.CJoJWuYFvm5TyVvs0mEbL6paLZdd8O0EpLwea-9rAvM" http://localhost:8080/api/customers
```

Voeg een auto toe aan de database
```bash
curl -v -X POST http://localhost:8080/api/cars \
-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMzkyODc4MywiZXhwIjoxNzMzOTY0NzgzfQ.CJoJWuYFvm5TyVvs0mEbL6paLZdd8O0EpLwea-9rAvM" \
-H "Expect:" \
-H 'Content-Type: application/json; charset=utf-8' \
--data-binary @- << EOF
{ 
        "make": "Tesla",
        "model": "Model 3",
        "version": 2022,
        "numberOfDoors": 4,
        "co2Emission": 12.0,
        "grossPrice": 40000,
        "nettPrice": 30000
}
EOF
```

Voeg een klant toe aan de database
```bash
curl -v -X POST http://localhost:8080/api/customers \
-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMzkyODc4MywiZXhwIjoxNzMzOTY0NzgzfQ.CJoJWuYFvm5TyVvs0mEbL6paLZdd8O0EpLwea-9rAvM" \
-H "Expect:" \
-H 'Content-Type: application/json; charset=utf-8' \
--data-binary @- << EOF
{ 
    	"name":"Marko",
    	"street":"deStreek",
    	"houseNumber":"19",
    	"zipCode":"9321 VP",
    	"place":"Peize",
    	"emailAddress":"msd_dol@hotmail.com",
    	"phoneNumber":"0631037049"
}
EOF
```

Koppel een klant aan een auto middels een contract
```bash
curl -v -X POST http://localhost:8080/api/contracts \
-H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMzkyODc4MywiZXhwIjoxNzMzOTY0NzgzfQ.CJoJWuYFvm5TyVvs0mEbL6paLZdd8O0EpLwea-9rAvM" \
-H "Expect:" \
-H 'Content-Type: application/json; charset=utf-8' \
--data-binary @- << EOF
{ 
    "car": {
        "id": 1,
        "make": "Tesla",
        "model": "Model 3",
        "version": 2022,
        "numberOfDoors": 4,
        "co2Emission": 12.0,
        "grossPrice": 40000,
        "nettPrice": 30000
    },
    "customer": {
        "id" : 1,
    	"name":"Marko",
    	"street":"deStreek",
    	"houseNumber":"19",
    	"zipCode":"9321 VP",
    	"place":"Peize",
    	"emailAddress":"msd_dol@hotmail.com",
    	"phoneNumber":"0631037049"
    	},
    "startDate": "2024-01-01",
    "endDate": "2025-01-01",
    "mileage": 200000,
    "interestRate": 4.5
}
EOF
```

Bekijk alle klanten die zijn opgeslagen in de database
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMzkyODc4MywiZXhwIjoxNzMzOTY0NzgzfQ.CJoJWuYFvm5TyVvs0mEbL6paLZdd8O0EpLwea-9rAvM" http://localhost:8080/api/customers
```

Bekijk alle autos die zijn opgeslagen in de database
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMzkyODc4MywiZXhwIjoxNzMzOTY0NzgzfQ.CJoJWuYFvm5TyVvs0mEbL6paLZdd8O0EpLwea-9rAvM" http://localhost:8080/api/cars
```

Bekijk alle contracten die zijn opgeslagen in de database
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMzkyODc4MywiZXhwIjoxNzMzOTY0NzgzfQ.CJoJWuYFvm5TyVvs0mEbL6paLZdd8O0EpLwea-9rAvM" http://localhost:8080/api/contracts
```

---

## Toekomstige Uitbreidingen

- **Authenticatie en autorisatie**: Voeg ondersteuning toe voor OAuth2.
- **Volledige Unit tests**: Ontwikkel unit tests voor alle API functies.
- **Validatie**: Implementeer uitgebreide validatieregels voor invoerdata.
- **Frontend**: Ontwikkel een gebruikersinterface om de API te gebruiken.

---

## Bijdragen

1. Fork de repository.
2. Maak een nieuwe branch:
   ```bash
   git checkout -b feature/naam-feature
   ```
3. Commit je wijzigingen:
   ```bash
   git commit -m "Voeg feature toe"
   ```
4. Push naar de branch:
   ```bash
   git push origin feature/naam-feature
   ```
5. Open een pull request.

---

## Licentie

Deze applicatie is gelicenseerd onder de MIT-licentie. Raadpleeg het [LICENSE-bestand](LICENSE) voor meer informatie.

---

Met deze README kunnen nieuwe gebruikers snel begrijpen wat de Car Lease API doet, hoe ze de applicatie kunnen installeren en gebruiken, en hoe ze kunnen bijdragen aan de ontwikkeling.
