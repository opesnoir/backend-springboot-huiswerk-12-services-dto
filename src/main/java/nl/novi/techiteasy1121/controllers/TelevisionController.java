package nl.novi.techiteasy1121.controllers;


import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionController {

    // Vorige week maakten we nog gebruik van een List<String>, nu gebruiken we de repository met een echte database.
    // We injecteren de repository hier via de constructor, maar je mag ook @Autowired gebruiken.
    private final TelevisionRepository televisionRepository;

    // Constructor injection
    public TelevisionController(TelevisionRepository televisionRepository){
        this.televisionRepository = televisionRepository;
    }

    // We hebben hier de RequestParam "brand" toegevoegd om te laten zien hoe dat werkt.
    // Als de gebruiker een brand invult, dan zoeken we op brand, anders returnen we alle televisions.
    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand) {

        List<Television> televisions;

        if (brand == null){

            // Vul de televisions lijst met alle televisions
            televisions = televisionRepository.findAll();


        } else {
            // Vul de televisions lijst met alle television die een bepaald brand hebben
            televisions = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        }

        // Return de televisions lijst en een 200 status
        return ResponseEntity.ok().body(televisions);

    }




    // Return 1 televisie met een specifiek id
    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable("id") Long id) {

        // Haal de television met het gegeven id uit de database.
        // Het Optional datatype betekend "wel of niet". Als er geen television gevonden wordt, dan is de Optional empty,
        // maar als er wel een television gevonden wordt, dan staat de television in de Optional en kun je deze er uit
        // halen met de get-methode. Op deze manier krijg je niet meteen een error als de tv niet in de database voorkomt.
        // Je kunt dat probleem zelf oplossen. In dit geval doen we dat door een RecordNotFoundException op te gooien met een message.
        Optional<Television> television = televisionRepository.findById(id);

        // Check of de optional empty is. Het tegenovergestelde alternatief is "television.isPresent()"
        if (television.isEmpty()){

            // Als er geen television in de optional staat, roepen we hier de RecordNotFoundException constructor aan met message.
            throw new RecordNotFoundException("No television found with id: " + id );

        } else {
            // Als er wel een television in de optional staat, dan halen we die uit de optional met de get-methode.
            Television television1 = television.get();

            // Return de television en een 200 status
            return ResponseEntity.ok().body(television1);
        }

    }

    // We geven hier een television mee in de parameter. Zorg dat je JSON object exact overeenkomt met het Television object.
    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@RequestBody Television television) {

        // Sla de nieuwe tv in de database op met de save-methode van de repository
        Television returnTelevision = televisionRepository.save(television);

        // Return de gemaakte television en een 201 status
        return ResponseEntity.created(null).body(returnTelevision);

    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") Long id) {

        // Verwijder de television met het opgegeven id uit de database.
        televisionRepository.deleteById(id);

        // Return een 204 status
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {

        // Haal de aan te passen tv uit de database met het gegeven id
        Optional<Television> television = televisionRepository.findById(id);

        // Als eerste checken we of de aan te passen tv wel in de database bestaat.
        if (television.isEmpty()){

            throw new RecordNotFoundException("No television found with id: " + id);

        }else {
            // Verander alle waardes van de television uit de database naar de waardes van de television uit de parameters.
            // Behalve de id. Als je de id veranderd, dan wordt er een nieuw object gemaakt in de database.
            Television television1 = television.get();
            television1.setAmbiLight(newTelevision.getAmbiLight());
            television1.setAvailableSize(newTelevision.getAvailableSize());
            television1.setAmbiLight(newTelevision.getAmbiLight());
            television1.setBluetooth(newTelevision.getBluetooth());
            television1.setBrand(newTelevision.getBrand());
            television1.setHdr(newTelevision.getHdr());
            television1.setName(newTelevision.getName());
            television1.setOriginalStock(newTelevision.getOriginalStock());
            television1.setPrice(newTelevision.getPrice());
            television1.setRefreshRate(newTelevision.getRefreshRate());
            television1.setScreenQuality(newTelevision.getScreenQuality());
            television1.setScreenType(newTelevision.getScreenType());
            television1.setSmartTv(newTelevision.getSmartTv());
            television1.setSold(newTelevision.getSold());
            television1.setType(newTelevision.getType());
            television1.setVoiceControl(newTelevision.getVoiceControl());
            television1.setWifi(newTelevision.getWifi());
            // Sla de gewijzigde waarden op in de database onder dezelfde id. Dit moet je niet vergeten.
            Television returnTelevision = televisionRepository.save(television1);
            // Return de nieuwe versie van deze tv en een 200 code
            return ResponseEntity.ok().body(returnTelevision);
        }

    }

}
