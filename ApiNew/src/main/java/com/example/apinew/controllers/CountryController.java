package com.example.apinew.controllers;

import com.example.apinew.Models.Country;
import com.example.apinew.repository.CountryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;



    @GetMapping("/country")
    public ResponseEntity<List<Country>> getCountry(){
        List<Country> country = countryRepository.findAll();

        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @GetMapping("/country/{idCountry}")
    public ResponseEntity<Country> oneCountry(@PathVariable Long idCountry){
        Optional<Country> optionalCountry = countryRepository.findById(idCountry);

        if(optionalCountry.isPresent()){
            return new ResponseEntity<>(optionalCountry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/country")
    public ResponseEntity<Country> createCountry(@Valid @RequestBody Country country){
        return new ResponseEntity<>(countryRepository.save(country), HttpStatus.CREATED);
    }

    @PutMapping("/country/{idCountry}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long idCountry,
                                                                 @Valid @RequestBody Country country){
        Optional<Country> optionalCountry = countryRepository.findById(idCountry);

        if(optionalCountry.isEmpty()){
            return new ResponseEntity<>(optionalCountry.get(), HttpStatus.OK);
        }

        Country country1 = optionalCountry.get();
        country1.setIdCountry(idCountry);
        country1.setNameCountry(country.getNameCountry());

        Country countryUpdate = countryRepository.save(country1);
        return new ResponseEntity<>(countryUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/country/{idCountry}")
    public ResponseEntity<?> deleteCountry(@PathVariable Long idCountry){
        Optional<Country> optionalCountry = countryRepository.findById(idCountry);

        if(optionalCountry.isEmpty()){
            return new ResponseEntity<>(optionalCountry.get(), HttpStatus.OK);
        }
        Country country1 = optionalCountry.get();

        countryRepository.delete(country1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
