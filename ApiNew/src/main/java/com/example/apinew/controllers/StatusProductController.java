package com.example.apinew.controllers;

import com.example.apinew.Models.StatusProduct;
import com.example.apinew.repository.StatusProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatusProductController {

    @Autowired
    private StatusProductRepository statusProductRepository;

    @GetMapping("/statusProduct")
    public ResponseEntity<List<StatusProduct>> getStatusProduct(){
        List<StatusProduct> statusProducts = statusProductRepository.findAll();

        return new ResponseEntity<>(statusProducts, HttpStatus.OK);
    }
}
