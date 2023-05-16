package nl.novi.techiteasy1121.service;

import nl.novi.techiteasy1121.dto.TelevisionDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Voeg de Service annotatie boven de klasse toe.
@Service
public class TelevisionService {
    //Maak in de TelevisionService een private variabele aan voor de TelevisionRepository.
    @Autowired
    private final TelevisionRepository televisionRepository;

    //constructor om de repository te importeren
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionDto> getTelevisions(){
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionDto televisionDto = new TelevisionDto();
            televisionDto.setId(televisionDto.getId());
            televisionDto.setName(television.getName());
            televisionDtos.add(televisionDto);
        }
        return televisionDtos;
    }

    //variabelen doorgeven aan de methode, en zorgen dat je de setName(television.getName()... stijl gebruikt
    public TelevisionDto getTelevision(){
        Television television = televisionRepository.findTelevisionByBrandAndNameEqualsIgnoreCase(brand, name) .orElseThrow(()-> new RecordNotFoundException("television not found"));

        TelevisionDto televisionDto = new TelevisionDto();
        televisionDto.setBrand(television.getBrand());
        televisionDto.setName(television.getName());

        return televisionDto;
    }

    public TelevisionDto saveTelevision(TelevisionDto televisionDto) {
        Television television = new Television();
        television.setBrand(televisionDto.getBrand());
        television.setName(televisionDto.getName());

        try {
            television = televisionRepository.save(television);
        } catch (Exception e) {
            // Handel de exceptie af indien nodig
        }

        televisionDto.setId(television.getId());

        return televisionDto;
    }




}
