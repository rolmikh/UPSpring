package com.example.apinew.controllers;

import com.example.apinew.Models.Provider;
import com.example.apinew.repository.ProviderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProviderController {
    @Autowired
    private ProviderRepository providerRepository;



    @GetMapping("/provider")
    public ResponseEntity<List<Provider>> getProvider(){
        List<Provider> provider = providerRepository.findAll();

        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @GetMapping("/provider/{idProvider}")
    public ResponseEntity<Provider> oneProvider(@PathVariable Long idProvider){
        Optional<Provider> optionalProvider = providerRepository.findById(idProvider);

        if(optionalProvider.isPresent()){
            return new ResponseEntity<>(optionalProvider.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/provider")
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider){
        return new ResponseEntity<>(providerRepository.save(provider), HttpStatus.CREATED);
    }

    @PutMapping("/provider/{idProvider}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long idProvider,
                                                         @Valid @RequestBody Provider provider){
        Optional<Provider> optionalProvider = providerRepository.findById(idProvider);

        if(optionalProvider.isEmpty()){
            return new ResponseEntity<>(optionalProvider.get(), HttpStatus.OK);
        }

        Provider provider1 = optionalProvider.get();
        provider1.setIdProvider(idProvider);
        provider1.setNameProvider(provider.getNameProvider());
        provider1.setCountries(provider.getCountries());

        return new ResponseEntity<>(providerRepository.save(provider1), HttpStatus.OK);
    }

    @DeleteMapping("/provider/{idProvider}")
    public ResponseEntity<?> deleteProvider(@PathVariable Long idProvider){
        Optional<Provider> optionalProvider = providerRepository.findById(idProvider);

        if(optionalProvider.isEmpty()){
            return new ResponseEntity<>(optionalProvider.get(), HttpStatus.OK);
        }
        Provider provider1 = optionalProvider.get();

        providerRepository.delete(provider1);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
