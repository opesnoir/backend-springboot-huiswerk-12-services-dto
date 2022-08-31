# Aandachtspunten

## Stijl

- Alle map-namen beginnen met een kleine letter, alle class-namen met een hoofdletter.

- Namen van klassen/methodes/database zijn betekenisvol.

## Functionaliteit

- Aan alle randvoorwaarden moet zijn voldaan

- De repository moet goed aan de service gekoppeld zijn met `@Autowired` (of via een constructor, dan is `@Autowired` alleen verplicht als je meerdere constructors hebt)

- De POM.xml bevat dependencies voor _Spring Web_, _Spring Data Jpa_ en _PostgreSQL Driver_

- In de application.properties staat _spring.sql.init.mode=always_ i.p.v. _spring.datasource.initialization-mode=always_

- De database is bij voorkeur ook gevuld (bijvoorbeeld met een data.sql bestand). 