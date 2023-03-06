# Aandachtspunten

## Stijl

- Alle map-namen beginnen met een kleine letter en in meervoud. Alle class-namen met een hoofdletter en in enkelvoud.

- Namen van klassen/methodes/database zijn betekenisvol.

- Goede (maven) map structuur.

```markdown
└── main
     ├── java
          └── nl.novi.techiteasy
               └── controllers
               ├── models
               ├── repositories
               └── exceptions
     └── resources
└── test
     ├── java
     └── resources
```
_Let op:_

_"nl.novi.techiteasy" mag ook iets anders zijn, zoals "com.jounaam.applicatienaam"_

_De volgorde van de mappen, controllers, models, repositories en exceptions is niet belangrijk._

## Functionaliteit

- Aan alle randvoorwaarden moet zijn voldaan

- De repository moet goed aan de service gekoppeld zijn met constructor injection (of via `@Autowired`)

- De POM.xml bevat dependencies voor _Spring Web_, _Spring Data Jpa_ en _PostgreSQL Driver_

- In de application.properties staat _spring.sql.init.mode=always_ i.p.v. _spring.datasource.initialization-mode=always_

- De database is bij voorkeur ook gevuld (bijvoorbeeld met een data.sql bestand). 
